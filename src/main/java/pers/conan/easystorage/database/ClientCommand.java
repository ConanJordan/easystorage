package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.operate.DeleteOperate;
import pers.conan.easystorage.operate.OperateType;
import pers.conan.easystorage.operate.PreparedStatementType;
import pers.conan.easystorage.operate.SelectOperate;
import pers.conan.easystorage.util.CommonUtil;

import java.sql.Connection;
import java.sql.SQLException;
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
    private Collection<? extends Structure> targets;
    private String seq;
    private String table;
    
    /**
     * 查询操作
     */
    private SelectOperate select;
    
    /**
     * 删除操作
     */
    private DeleteOperate delete;
    
    /**
     * 目标结构体的类
     */
    private Class<? extends  Structure> structure;
    
    /**
     * 目标结构体对象的流
     */
    private Stream<? extends Structure> resultStream;
    
    /**
     * 执行成功的记录数
     */
    private int resultCount = 0;

    /**
     * 操作类型
     */
    private OperateType operateType;

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

    public Collection<? extends Structure> getTargets() {
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
    
    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * 外部获取实例化对象的方法
     * @param connection
     * @return
     */
    public static ClientCommand build(Connection connection) {
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
    public ClientCommand select(String table, Class<? extends Structure> structure) throws Exception {
        return this.select(table, null, null, structure);
    }

    @Override
    public ClientCommand select(String table, String condition, Object[] args, Class<? extends Structure> structure) throws Exception {
        return this.select(table, condition, null, args, structure);
    }

    @Override
    public ClientCommand select(String table, String condition, String sort, Object[] args, Class<? extends Structure> structure) throws Exception {

        // 设置属性值
        this.table = table;
        this.condition = condition;
        this.sort = sort;
        this.args = args;
        this.structure = structure;
        
        if (this.select == null) {
            this.select = SelectOperate.build(this); // 初始化查询操作
        } else {
            this.select.reset(); // 重置查询操作
        }
        
        this.operateType = OperateType.SELECT;
        this.select.setPsType(PreparedStatementType.CONDITION); // 设置预编译类型
        
        this.select.prepare();
        
        return this;
        
    }

    @Override
    public ClientCommand Select(String sql, Object[] args, Class<? extends Structure> structure) throws Exception {
        
        // 设置SQL语句和参数
        this.SQL = sql;
        this.args = args;
        this.structure = structure;

        if (this.select == null) {
            this.select = SelectOperate.build(this); // 初始化查询操作
        } else {
            this.select.reset(); // 重置查询操作
        }
        
        this.operateType = OperateType.SELECT;
        this.select.setPsType(PreparedStatementType.SQL); // 设置预编译类型

        this.select.prepare();
        
        return this;
    }

    @Override
    public ClientCommand insert(String table, Structure target, Class<? extends Structure> structure) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Structure target, String seq, Class<? extends Structure> structure) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets, Class<? extends Structure> structure) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets, String seq, Class<? extends Structure> structure) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets, String seq, boolean isParallel, Class<? extends Structure> structure) {
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
    public ClientCommand update(String table, Collection<? extends Structure> targets) {
        return null;
    }
    
    @Override
    public ClientCommand delete(String sql, Object[] args) throws Exception {
        // 设置属性
        this.SQL = sql;
        this.args = args;
        
        if (CommonUtil.isEmpty(this.delete)) {
            this.delete = DeleteOperate.build(this); // 创建删除操作对象
        } else {
            this.delete.reset(); // 重置删除操作对象
        }
        
        this.delete.setPsType(PreparedStatementType.SQL); // 设置预编译类型
        this.operateType = OperateType.DELETE; // 设置操作类型
        this.delete.prepare();
        
        return this;
    }

    @Override
    public ClientCommand delete(String table, Structure target, Class<? extends Structure> structure) throws Exception {
        // 设置属性
        this.table = table;
        this.target = target;
        this.structure = structure;
        
        if (CommonUtil.isEmpty(this.delete)) {
            this.delete = DeleteOperate.build(this); // 创建删除操作对象
        } else {
            this.delete.reset(); // 重置删除操作对象
        }
        
        this.delete.setPsType(PreparedStatementType.TARGET); // 设置预编译类型
        this.operateType = OperateType.DELETE; // 设置操作类型
        this.delete.prepare();
        
        return this;
    }

    @Override
    public ClientCommand delete(String table, String condition, Object[] args) throws Exception {
        // 设置属性
        this.table = table;
        this.condition = condition;
        this.args = args;

        if (CommonUtil.isEmpty(this.delete)) {
            this.delete = DeleteOperate.build(this); // 创建删除操作对象
        } else {
            this.delete.reset(); // 重置删除操作对象
        }
        
        this.delete.setPsType(PreparedStatementType.CONDITION); // 设置预编译类型
        this.operateType = OperateType.DELETE; // 设置操作类型
        this.delete.prepare();
        
        return this;
    }

    @Override
    public ClientCommand delete(String table, Collection<? extends Structure> targets, Class<? extends Structure> structure) throws Exception {
        // 设置属性
        this.table = table;
        this.targets = targets;
        this.structure = structure;

        if (CommonUtil.isEmpty(this.delete)) {
            this.delete = DeleteOperate.build(this); // 创建删除操作对象
        } else {
            this.delete.reset(); // 重置删除操作对象
        }
        
        this.delete.setPsType(PreparedStatementType.CONDITION); // 设置预编译类型
        this.operateType = OperateType.DELETE; // 设置操作类型
        this.delete.prepare();
        
        return this;
    }

    @Override
    public ClientCommand execute() throws SQLException {

        switch (this.operateType) { // 判断当前操作类型
            case SELECT: // 查询
                this.select.operate();
                return this;

            case INSERT: // 插入

            case UPDATE: // 更新

            case DELETE: // 删除
                this.delete.operate();
                return this;
        }

        return this;
    }

    public List<? extends Structure> toList() {
        return this.resultStream.collect(Collectors.toList());
    }

    public Stream<? extends Structure> toStream() {
        return this.resultStream;
    }
    
    public int toResultCount() {
        return this.resultCount;
    }

}
