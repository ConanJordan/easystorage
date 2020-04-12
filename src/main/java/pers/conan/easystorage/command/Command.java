package pers.conan.easystorage.command;

import pers.conan.easystorage.annotation.Structure;

import java.sql.Connection;
import java.util.List;

/**
 * 类：SQL指令
 */
public abstract class Command implements Executable {

    /**
     * 数据库连接
     */
    protected Connection connection;

    public Command(Connection connection) {
        this.connection = connection;
    }

    /**
     * 执行(SQL语句)
     *
     * @return 查询结果集
     */
    public abstract List<Structure> execute();

    /**
     * 执行(SQL语句)
     *
     * @return 记录条数(INSERT, UPDATE, DELETE)
     */
    public abstract int executeUpdate();
}
