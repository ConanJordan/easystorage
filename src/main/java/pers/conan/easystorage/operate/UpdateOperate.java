package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.annotation.PrimaryKey;
import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.util.CommonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * 类：删除操作
 *
 * @author Conan
 */
public class UpdateOperate implements Operate {

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

    private void bySql() throws SQLException {

        this.prst = this.connection.prepareStatement(this.SQL); // 预编译

        // 设置参数
        if (CommonUtil.isEmpty(this.args) == false) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, args[i - 1]);
            }
        }
    }

    private void byTarget() throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(this.table); // 表名
        sql.append(" SET ");

        EntityParse.getNonPkFields(this.structure) // 获取目标实体类的非主键的属性的流ß
                .forEach(field -> {
                    sql.append(field.getAnnotation(Column.class).value());
                    sql.append(" = ?, ");
                });

        sql.substring(0, sql.length() - 2);

        sql.append(" WHERE ");

        EntityParse.getPkFields(this.structure) // 获取目标实体类的非主键的属性的流ß
                .forEach(field -> {
                    sql.append(field.getAnnotation(Column.class).value());
                    sql.append(" = ? AND ");
                });

        sql.substring(0, sql.length() - 4);

        this.SQL = sql.toString();

        this.prst = this.connection.prepareStatement(this.SQL); // 预编译

        // TODO 设置参数

    }
}
