package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.util.CommonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

/**
 * 类：删除操作
 *
 * @author Conan
 */
public final class UpdateOperate extends PreCompile implements Operate {

    private Connection connection;
    private PreparedStatement prst;
    private String table;
    private String SQL;
    private String Condition;
    private Object[] args;
    private Class<? extends Structure> structure;
    private Structure target;
    private Collection<? extends Structure> targets;
    private PreparedStatementType psType;
    private int index;


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

        EntityParse.getNonPkFields(this.structure) // 获取目标实体类的非主键的属性的流
                .forEach(field -> {
                    sql.append(field.getAnnotation(Column.class).value());
                    sql.append(" = ?, ");
                });

        sql.substring(0, sql.length() - 2);

        sql.append(" WHERE ");

        EntityParse.getPkFields(this.structure) // 获取目标实体类的主键的属性的流
                .forEach(field -> {
                    sql.append(field.getAnnotation(Column.class).value());
                    sql.append(" = ? AND ");
                });

        sql.substring(0, sql.length() - 4);

        this.SQL = sql.toString();

        this.prst = this.connection.prepareStatement(this.SQL); // 预编译

        this.index = 1; // 设置参数用的索引是从1开始的
        EntityParse.getNonPkFields(this.structure) // 获取目标实体类的非主键的属性的流
                   .forEach(field-> {
                       try {
                        this.prst.setObject(this.index++, EntityParse.getFieldValue(this.structure, field, this.target)); // 获取属性的值，设置参数
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                   });
        
        EntityParse.getPkFields(this.structure) // 获取目标实体类的主键的属性的流
                   .forEach(field -> {
                       try {
                           this.prst.setObject(this.index++, EntityParse.getFieldValue(this.structure, field, this.target)); // 获取属性的值，设置参数
                       } catch (Exception e) {
                           throw new RuntimeException(e);
                       }
                   });

    }

    /**
     * 基于目标对象集合预编译
     */
    @Override
    protected void byTargets() throws Exception {
        
    }
    
    /**
     * 基于条件预编译
     */
    @Override
    protected void byCondition() throws Exception {
        
    }

}
