package pers.conan.easystorage.command;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.parse.Module;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * 类：Insert指令
 */
public class InsertCommand extends Command {

    /**
     * 要插入的结构体对象集合
     */
    private List<Structure> insertions = new ArrayList<Structure>();

    public InsertCommand(Connection connection) {
        super(connection);
    }

    public List<Structure> getInsertions() {
        return insertions;
    }

    public void setInsertions(List<Structure> insertions) {
        this.insertions = insertions;
    }

    /**
     * 添加要插入的对象
     * @param insertion
     */
    public void put(Structure insertion) {
        this.insertions.add(insertion);
    }

    /**
     * 执行(SQL语句)
     *
     * @return 查询结果集
     */
    @Override
    public List<Structure> execute() {
        return null;
    }

    /**
     * 执行(SQL语句)
     *
     * @return 记录条数(INSERT, UPDATE, DELETE)
     */
    @Override
    public int executeUpdate() {
        return 0;
    }

    /**
     * 准备
     *
     * @return
     */
    @Override
    public int prepare() {
        return 0;
    }
}
