package pers.conan.easystorage.operate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.CommonUtil;

/**
 * 类：删除操作
 *
 * @author Conan
 */
public class DeleteOperate implements Operate {
    
    private Connection connection;
    private PreparedStatement prst = null;
    private String table;
    private String condition;
    private String SQL;
    private Object[] args;
    private Structure target;
    private Collection<Structure> targets;
    private Class<? extends Structure> structure;
    private long deleteCount = 0L;
    private ClientCommand command;
    
    /**
     * 构造方法
     * 不对外开放
     */
    private DeleteOperate() {
        
    }
    
    /**
     * 外部获取本类实体对象的接口
     * @param command
     * @return
     */
    public static DeleteOperate build(ClientCommand command) {
        DeleteOperate instance = new DeleteOperate();

        instance.connection = command.getConnection();
        instance.condition = command.getCondition();
        instance.SQL = command.getSQL();
        instance.target = command.getTarget();
        instance.targets = command.getTargets();
        instance.table = command.getTable();
        instance.args = command.getArgs();
        instance.structure = command.getStructure();
        instance.command = command;

        // 设置属性

        return instance; // 返回实例对象
    }
    
    /**
     * 准备SQL语句
     */
    @Override
    public void prepare() throws Exception {
        if (CommonUtil.isEmpty(this.table)) {
            throw new Exception("The table should not be empty when attempting to delete records from it.");
        }
        
        // TODO
    }
    
    /**
     * 根据条件设置SQL语句
     * @throws SQLException 
     */
    private void byCondition() throws SQLException {
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        
        sql.append(this.table);
        
        if (CommonUtil.isEmpty(this.condition) == false) {
            sql.append(" WHERE ");
            sql.append(this.condition);
        }
        
        this.SQL = sql.toString(); // 获取SQL语句
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        if (CommonUtil.isEmpty(this.args) == false) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, this.args[i - 1]);
            }
        }
    }
    
    /**
     * 根据SQL语句设置SQL语句
     * @throws SQLException 
     */
    private void bySql() throws SQLException {
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        if (CommonUtil.isEmpty(this.args) == false) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, this.args[i - 1]);
            }
        }
    }
    
    /**
     * 根据目标对象设置SQL语句
     */
    private void byTarget() {
        
    }
    
    /**
     * 根据目标对象集合设置SQL语句
     */
    private void byTargets() {
        
    }

    /**
     * 执行SQL操作
     */
    @Override
    public void operate() {

    }
}