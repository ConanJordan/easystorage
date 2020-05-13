package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 类：查询操作
 */
public class SelectOperate implements Operate {

    private ClientCommand command;
    private Connection connection = null;
    private PreparedStatement prst = null;
    private ResultSet rs = null;
    private String SQL;
    private String table;
    private String condition;
    private String sort;
    private Object[] args;
    private Class<? extends  Structure> structure;
    private Stream<? extends Structure> resultStream;

    /**
     * 构造方法
     * 不对外开放
     */
    private SelectOperate(){

    }

    /**
     * 外部获取本类实例化对象的接口
     */
    public static SelectOperate build(ClientCommand command) {
        SelectOperate instance = new SelectOperate(); // 获取本类的实例化对象

        // 设置实例化对象的各个属性
        instance.connection = command.getConnection();
        instance.SQL = command.getSQL();
        instance.condition = command.getCondition();
        instance.args = command.getArgs();
        instance.table = command.getTable();
        instance.sort = command.getSort();
        instance.structure = command.getStructure();
        instance.command = command;

        return instance; // 返回该实例化对象
    }

    @Override
    public ClientCommand operate() {
        return null; // TODO
    }

    private ClientCommand bySql() {
        Objects.requireNonNull(this.SQL);  // SQL语句不能是空白

        try {
            // 准备要执行的SQL语句
            this.prst = this.connection.prepareStatement(this.SQL);

            // 设置参数
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, this.args[i]);
            }

            // 执行SQL语句
            this.rs = this.prst.executeQuery();

            // TODO 操作结果集

            // 获取实体对象的流
            this.resultStream = EntityOperate.createEntities(this.rs, this.structure);

            this.command.setResultStream(this.resultStream);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放数据库资源
            Sql.close(new AutoCloseable[]{this.prst, this.rs});
        }

        return this.command;
    }
}
