package pers.conan.easystorage.operate;

import pers.conan.easystorage.database.ClientCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 类：查询操作
 */
public class SelectOperate implements Operate {

    private ClientCommand command;
    private Connection connection;
    private PreparedStatement prst;
    private ResultSet rs;
    private String SQL;
    private String table;
    private String condition;
    private String sort;
    private Object[] args;

    private SelectOperate(
            Connection connection,
            String SQL, String table,
            String condition,
            String sort,
            Object[] args,
            ClientCommand command) {
        this.connection = connection;
        this.SQL = SQL;
        this.table = table;
        this.condition = condition;
        this.sort = sort;
        this.args = args;
        this.command = command;
    }

    public static SelectOperate build(
            Connection connection,
            String SQL,
            String table,
            String condition,
            String sort,
            Object[] args,
            ClientCommand command) {
        return new SelectOperate(connection, SQL, table, condition, sort, args, command);
    }

    @Override
    public ClientCommand operate() {
        return null; // TODO
    }
}
