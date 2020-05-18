package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.CommonUtil;
import pers.conan.easystorage.util.Sql;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类：更新操作
 *
 * @author Conan
 */
public final class UpdateOperate extends PreCompile implements Operate {
    
    /**
     * 目标对象的属性集合
     * 用于设置更新值和更新条件
     */
    private List<Field> usedFields = new ArrayList<>();
    
    /**
     * 构建SQL语句
     */
    private StringBuilder sqlBuilder;
    
    /**
     * 构造方法
     * 不对外开放
     */
    private UpdateOperate() {
        
    }
    
    /**
     * 外部获取本类实体对象的接口
     * @param command
     * @return
     */
    public static UpdateOperate build(ClientCommand command) {
        UpdateOperate instance = new UpdateOperate();

        instance.connection = command.getConnection();
        instance.condition = command.getCondition();
        instance.SQL = command.getSQL();
        instance.target = command.getTarget();
        instance.targets = command.getTargets();
        instance.table = command.getTable();
        instance.args = command.getArgs();
        instance.structure = command.getStructure();
        instance.command = command;

        // 设置属性

        return instance; // 返回实例对象
    }
    
    /**
     * 重置本类的实例化对象
     */
    public void reset() {
        this.connection = command.getConnection();
        this.condition = command.getCondition();
        this.SQL = command.getSQL();
        this.target = command.getTarget();
        this.targets = command.getTargets();
        this.table = command.getTable();
        this.args = command.getArgs();
        this.structure = command.getStructure();
    }


    /**
     * 准备SQL语句
     * @throws Exception
     */
    @Override
    public void prepare() throws Exception {
        
        switch(this.psType) { // 判断预编译类型
            case SQL:
                this.bySql();
                break;
                
            case TARGET:
                this.byTarget();
                break;
                
            case TARGETS:
                this.byTargets();
                break;
                
            default: // 没有预编译类型或条件预编译类型
                throw new Exception();
        }
    }

    /**
     * 执行SQL操作
     * @throws SQLException 
     */
    @Override
    public void operate() throws SQLException {
        try {
            if (this.psType != PreparedStatementType.TARGETS) { // 不需要批量处理
                this.resultCount = this.prst.executeUpdate(); // 获取成功执行的记录数 
            } else { // 需要批量处理
                this.resultCount = Arrays.stream(this.prst.executeBatch())
                                .reduce(0, (acc, element) -> acc + element); // 获取成功执行的记录数       
            }
            
            this.command.setResultCount(this.resultCount); // 设置成功执行的记录数
        } catch (SQLException e) {
            throw e;
        } finally {
            // 释放数据库资源
            Sql.close(new AutoCloseable[] {this.prst});
        }
    }

    /**
     * 基于SQL语句预编译
     */
    @Override
    protected void bySql() throws Exception {

        this.prst = this.connection.prepareStatement(this.SQL); // 预编译

        // 设置参数
        if (CommonUtil.isEmpty(this.args) == false) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, args[i - 1]);
            }
        }
    }

    /**
     * 基于目标对象预编译
     */
    @Override
    protected void byTarget() throws Exception {
        this.sqlBuilder = new StringBuilder("UPDATE ");
        this.sqlBuilder.append(this.table); // 表名
        this.sqlBuilder.append(" SET ");
        
        this.usedFields.clear(); // 清空目标对象的属性集合

        EntityParse.getNonPkFields(this.structure) // 获取目标实体类的非主键的属性的流
                .forEach(field -> {
                    this.sqlBuilder.append(field.getAnnotation(Column.class).value());
                    this.sqlBuilder.append(" = ?, ");
                    this.usedFields.add(field); // 放入目标对象的属性集合
                });

        this.sqlBuilder = new StringBuilder(this.sqlBuilder.substring(0, this.sqlBuilder.length() - 2));

        this.sqlBuilder.append(" WHERE ");

        EntityParse.getPkFields(this.structure) // 获取目标实体类的主键的属性的流
                .forEach(field -> {
                    this.sqlBuilder.append(field.getAnnotation(Column.class).value());
                    this.sqlBuilder.append(" = ? AND ");
                    this.usedFields.add(field); // 放入目标对象的属性集合
                });

        this.sqlBuilder = new StringBuilder(this.sqlBuilder.substring(0, this.sqlBuilder.length() - 4));

        // 添加传过来的condition(乐观排他等等)
        if (CommonUtil.isEmpty(this.condition) == false) {
            this.sqlBuilder.append(" AND ").append(this.condition);
        }

        this.SQL = this.sqlBuilder.toString();

        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置更新字段和主键参数
        for (int i = 1; i <= this.usedFields.size(); i ++) { // 参数的索引从1开始
            this.prst.setObject(i, EntityParse.getFieldValue(this.structure, this.usedFields.get(i - 1), this.target));
        }
        // 设置condition参数(乐观排他等)
        if (CommonUtil.isEmpty(this.args) == false) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(this.usedFields.size() + i, this.args[i - 1]);
            }
        }
    }

    /**
     * 基于目标对象集合预编译
     */
    @Override
    protected void byTargets() throws Exception {
        this.sqlBuilder = new StringBuilder("UPDATE ");
        this.sqlBuilder.append(this.table); // 表名
        this.sqlBuilder.append(" SET ");
        
        this.usedFields.clear(); // 清空目标对象的属性集合

        EntityParse.getNonPkFields(this.structure) // 获取目标实体类的非主键的属性的流
                .forEach(field -> {
                    this.sqlBuilder.append(field.getAnnotation(Column.class).value());
                    this.sqlBuilder.append(" = ?, ");
                    this.usedFields.add(field); // 放入目标对象的属性集合
                });

        this.sqlBuilder = new StringBuilder(this.sqlBuilder.substring(0, this.sqlBuilder.length() - 2));

        this.sqlBuilder.append(" WHERE ");

        EntityParse.getPkFields(this.structure) // 获取目标实体类的主键的属性的流
                .forEach(field -> {
                    this.sqlBuilder.append(field.getAnnotation(Column.class).value());
                    this.sqlBuilder.append(" = ? AND ");
                    this.usedFields.add(field); // 放入目标对象的属性集合
                });

        this.sqlBuilder = new StringBuilder(this.sqlBuilder.substring(0, this.sqlBuilder.length() - 4));

        this.SQL = this.sqlBuilder.toString();

        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        this.targets.stream()
                    .forEach(item -> {
                        for (int i = 1; i <= this.usedFields.size(); i ++) { // 参数的索引从1开始
                            try {
                                this.prst.setObject(i, EntityParse.getFieldValue(this.structure, this.usedFields.get(i - 1), item));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        try {
                            this.prst.addBatch(); // 批量预编译
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
    }
    
    /**
     * 基于条件预编译
     */
    @Override
    protected void byCondition() throws Exception {
        // Do not need to override.
    }

}
