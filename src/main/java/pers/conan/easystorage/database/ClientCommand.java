package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.operate.SelectOperate;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private Class<? extends  Structure> structure;
    private Stream<? extends Structure> resultStream;

    public Class<? extends Structure> getStructure() {
        return structure;
    }

    public void setResultStream(Stream<? extends Structure> resultStream) {
        this.resultStream = resultStream;
    }

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
     * 外部获取实例化对象的方法
     * @param connection
     * @return
     */
    public ClientCommand build(Connection connection) {
        return new ClientCommand(connection);
    }

    /**
     * 构造方法
     * 不对外开放
     * @param connection
     */
    private ClientCommand(Connection connection) {
        Objects.requireNonNull(connection); // 与数据库的连接必须真实有效
        this.connection = connection;
    }

    @Override
    public ClientCommand select(String table) throws Exception {
        return this.select(table, null, null);
    }

    @Override
    public ClientCommand select(String table, String condition, Object[] args) throws Exception {
        return this.select(table, condition, null, args);
    }

    @Override
    public ClientCommand select(String table, String condition, String sort, Object[] args) throws Exception {

        // 设置属性值
        this.table = table;
        this.condition = condition;
        this.sort = sort;
        this.args = args;
        
        if (this.select == null) {
            this.select = SelectOperate.build(this);
        }
        
        this.select.prepare();
        
        return this;
        
    }

    @Override
    public ClientCommand Select(String sql, Object[] args) throws Exception {
        
        // 设置SQL语句和参数
        this.SQL = sql;
        this.args = args;

        if (this.select == null) {
            this.select = SelectOperate.build(this);
        }
        
        this.select.prepare();
        
        return this;
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

    public List<? extends Structure> toList() {
        return this.resultStream.collect(Collectors.toList());
    }

    public Stream<? extends Structure> toStream() {
        return this.resultStream;
    }
}
