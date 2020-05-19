package pers.conan.easystorage.operate;

import java.sql.SQLException;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.CommonUtil;


/**
 * 类：插入操作
 *
 * @author Conan
 */
public class InsertOperate extends PreCompile implements Operate {

    /**
     * 构造方法
     * 不对外开放
     */
    private InsertOperate() {

    }

    /**
     * 外部获取本类实体对象的接口
     * @param command
     * @return
     */
    public static InsertOperate build(ClientCommand command) {

        InsertOperate instance = new InsertOperate();

        instance.connection = command.getConnection();
        instance.SQL = command.getSQL();
        instance.condition = command.getCondition();
        instance.target = command.getTarget();
        instance.targets = command.getTargets();
        instance.table = command.getTable();
        instance.command = command;

        // 设置属性

        return instance; // 返回实例对象
    }
    
    /**
     * 重置实例化对象
     */
    public void reset() {
        this.connection = command.getConnection();
        this.SQL = command.getSQL();
        this.condition = command.getCondition();
        this.target = command.getTarget();
        this.targets = command.getTargets();
        this.table = command.getTable();
    }

    /**
     * 准备SQL语句
     */
    @Override
    public void prepare() throws Exception {

        // TODO
        
    }

    /**
     * 执行SQL操作
     */
    @Override
    public void operate() {

    }

    /**
     * 基于SQL语句预编译
     * @throws SQLException 
     */
    @Override
    protected void bySql() throws SQLException {
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        if (CommonUtil.isEmpty(this.args) == false) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, this.args[i - 1]);
            }
        }
    }

    /**
     * 基于条件预编译
     */
    @Override
    protected void byCondition() throws Exception {
        // Do not need to override.
    }

    /**
     * 基于目标对象预编译
     */
    @Override
    protected void byTarget() throws Exception {
        // TODO Auto-generated method stub
        
    }

    /**
     * 基于目标对象集合预编译
     */
    @Override
    protected void byTargets() throws Exception {
        // TODO Auto-generated method stub
        
    }
}
