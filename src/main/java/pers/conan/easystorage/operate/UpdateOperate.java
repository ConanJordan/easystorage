package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.util.CommonUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
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
     * 准备SQL语句
     * @throws Exception
     */
    @Override
    public void prepare() throws Exception {

    }

    /**
     * 执行SQL操作
     */
    @Override
    public void operate() {

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
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(this.table); // 表名
        sql.append(" SET ");
        
        this.usedFields.clear(); // 清空目标对象的属性集合

        EntityParse.getNonPkFields(this.structure) // 获取目标实体类的非主键的属性的流
                .forEach(field -> {
                    sql.append(field.getAnnotation(Column.class).value());
                    sql.append(" = ?, ");
                    this.usedFields.add(field); // 放入目标对象的属性集合
                });

        sql.substring(0, sql.length() - 2);

        sql.append(" WHERE ");

        EntityParse.getPkFields(this.structure) // 获取目标实体类的主键的属性的流
                .forEach(field -> {
                    sql.append(field.getAnnotation(Column.class).value());
                    sql.append(" = ? AND ");
                    this.usedFields.add(field); // 放入目标对象的属性集合
                });

        sql.substring(0, sql.length() - 4);

        this.SQL = sql.toString();

        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        for (int i = 1; i <= this.usedFields.size(); i ++) { // 参数的索引从1开始
            this.prst.setObject(i, EntityParse.getFieldValue(this.structure, this.usedFields.get(i - 1), this.target));
        }
    }

    /**
     * 基于目标对象集合预编译
     */
    @Override
    protected void byTargets() throws Exception {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(this.table); // 表名
        sql.append(" SET ");
        
        this.usedFields.clear(); // 清空目标对象的属性集合

        EntityParse.getNonPkFields(this.structure) // 获取目标实体类的非主键的属性的流
                .forEach(field -> {
                    sql.append(field.getAnnotation(Column.class).value());
                    sql.append(" = ?, ");
                    this.usedFields.add(field); // 放入目标对象的属性集合
                });

        sql.substring(0, sql.length() - 2);

        sql.append(" WHERE ");

        EntityParse.getPkFields(this.structure) // 获取目标实体类的主键的属性的流
                .forEach(field -> {
                    sql.append(field.getAnnotation(Column.class).value());
                    sql.append(" = ? AND ");
                    this.usedFields.add(field); // 放入目标对象的属性集合
                });

        sql.substring(0, sql.length() - 4);

        this.SQL = sql.toString();

        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        this.targets.parallelStream()
                    .forEach(item -> {
                        for (int i = 1; i <= this.usedFields.size(); i ++) { // 参数的索引从1开始
                            try {
                                this.prst.setObject(i, EntityParse.getFieldValue(this.structure, this.usedFields.get(i - 1), item));
                                this.prst.addBatch(); // 批量预编译
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            } 
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
