package pers.conan.easystorage.command;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.parse.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类：Insert指令
 */
public class InsertCommand extends Command {

    /**
     * 要插入的结构体对象集合
     */
    private List<Structure> insertions = new ArrayList<Structure>();

    /**
     * 用于批量处理SQL语句的PreparedStatement集合
     */
    private Set<PreparedStatement> prsts = new HashSet<PreparedStatement>();

    /**
     * 每次批量处理的batch最大量
     */
    private final int BATCH_MAX_SIZE = 1000;

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

    private void editBatchPrst() {

    }
}
