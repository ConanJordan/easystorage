package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.operate.SelectOperate;

import java.sql.Connection;
import java.util.Collection;
import java.util.Objects;

/**
 * 类：客户端命令
 */
public class ClientCommand extends BaseCommand {

    private Connection connection;
    private String SQL;
    private String condition;
    private String sort;
    private Object[] args;
    private Structure target;
    private Collection<Structure> targets;
    private String seq;
    private String table;
    private SelectOperate select;

    public String getSort() {
        return sort;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getSQL() {
        return SQL;
    }

    public String getCondition() {
        return condition;
    }

    public Object[] getArgs() {
        return args;
    }

    public Structure getTarget() {
        return target;
    }

    public Collection<Structure> getTargets() {
        return targets;
    }

    public String getSeq() {
        return seq;
    }

    public String getTable() {
        return table;
    }

    public void setSelect(SelectOperate select) {
        this.select = select;
    }

    /**
     * 构造方法
     * @param connection
     */
    private ClientCommand(Connection connection) {
        Objects.requireNonNull(connection); // 与数据库的连接必须真实有效
        this.connection = connection;
    }

    @Override
    public ClientCommand select(String table) {
        return null;
    }

    @Override
    public ClientCommand select(String table, String condition, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand select(String table, String condition, String sort, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand Select(String sql, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Structure target) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Structure target, String seq) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets, String seq) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets, String seq, boolean isParallel) {
        return null;
    }

    @Override
    public ClientCommand update(String table, Structure target) {
        return null;
    }

    @Override
    public ClientCommand update(String table, Structure target, String condition, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand update(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public ClientCommand delete(String table, Structure target) {
        return null;
    }

    @Override
    public ClientCommand delete(String table, Structure target, String condition, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand delete(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public ClientCommand Execute() {
        return null;
    }
}
