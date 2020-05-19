package pers.conan.easystorage.operate;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.CommonUtil;
import pers.conan.easystorage.util.Sql;

import static java.util.stream.Collectors.*;

import java.lang.reflect.Field;

/**
 * 类：删除操作
 *
 * @author Conan
 */
public class DeleteOperate extends PreCompile implements Operate {
    
    /**
     * 构造方法
     * 不对外开放
     */
    private DeleteOperate() {
        
    }
    
    /**
     * 外部获取本类实体对象的接口
     * @param command
     * @return
     */
    public static DeleteOperate build(ClientCommand command) {
        DeleteOperate instance = new DeleteOperate();

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
     */
    @Override
    public void prepare() throws Exception {
        
        switch(this.psType) { // 判断预编译类型
            case SQL:
                this.bySql();
                break;
                
            case CONDITION:
                this.byCondition();
                break;
                
            case TARGET:
                this.byTarget();
                break;
                
            case TARGETS:
                this.byTargets();
                break;
        default: // 没有预编译类型
            throw new Exception();
        }
    }
    
    /**
     * 根据条件设置SQL语句
     * @throws SQLException 
     */
    @Override
    protected void byCondition() throws SQLException {
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        
        sql.append(this.table);
        
        if (CommonUtil.isNotEmpty(this.condition)) {
            sql.append(" WHERE ");
            sql.append(this.condition);
        }
        
        this.SQL = sql.toString(); // 获取SQL语句
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        if (CommonUtil.isNotEmpty(this.args)) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, this.args[i - 1]);
            }
        }
    }
    
    /**
     * 根据SQL语句设置SQL语句
     * @throws SQLException 
     */
    @Override
    protected void bySql() throws SQLException {
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        if (CommonUtil.isNotEmpty(this.args)) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, this.args[i - 1]);
            }
        }
    }
    
    /**
     * 根据目标对象设置SQL语句
     * @throws Exception
     */
    @Override
    protected void byTarget() throws Exception {
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(this.table);
        sql.append(" WHERE ");
        
        List<Field> pkFields = EntityParse.getPkFields(this.structure) // 获取目标实体类的作主键的属性的流
                               .collect(toList()); // 转换成有序列表
        
        for (int i = 0; i < pkFields.size(); i ++) { // 添加到WHERE条件中去
            sql.append(pkFields.get(i).getAnnotation(Column.class).value())
               .append(" = ? AND ");
        }
        
        this.SQL = sql.substring(0, sql.length() - 4);
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        this.editArgs(pkFields, this.target); // 设置参数
        
    }
    
    /**
     * 根据目标对象集合设置SQL语句
     * @throws Exception 
     */
    @Override
    protected void byTargets() throws Exception {
        
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(this.table);
        sql.append(" WHERE ");
        
        List<Field> pkFields = EntityParse.getPkFields(this.structure) // 获取目标实体类的作主键的属性的流
                               .collect(toList()); // 转换成有序列表
        
        for (int i = 0; i < pkFields.size(); i ++) { // 添加到WHERE条件中去
            sql.append(pkFields.get(i).getAnnotation(Column.class).value())
               .append(" = ? AND ");
        }
        
        this.SQL = sql.substring(0, sql.length() - 4);
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 批量预编译
        this.targets.stream()
            .forEach(item -> {
                try {
                    this.editArgs(pkFields, item); // 设置参数
                    this.prst.addBatch(); // 加入批量预编译
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        
    }
    
    /**
     * 设置参数
     * @param pkFields
     * @throws Exception
     */
    private void editArgs(List<Field> pkFields, Structure item) throws Exception {
        for (int i = 1; i <= pkFields.size(); i ++) {
            this.prst.setObject(i, EntityParse.getFieldValue(this.structure, pkFields.get(i - 1), item));
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
}
