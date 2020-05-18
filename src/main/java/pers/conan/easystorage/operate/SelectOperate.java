package pers.conan.easystorage.operate;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.Sql;

import java.sql.SQLException;

/**
 * 类：查询操作
 */
public class SelectOperate extends PreCompile implements Operate {

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

    /**
     * 重置实例化对象
     */
    public void reset() {
        this.connection = command.getConnection();
        this.SQL = command.getSQL();
        this.condition = command.getCondition();
        this.args = command.getArgs();
        this.table = command.getTable();
        this.sort = command.getSort();
        this.structure = command.getStructure();
    }

    /**
     * 执行SQL操作
     * @throws SQLException 
     */
    @Override
    public void operate() throws SQLException {
        // 执行SQL语句
        try {
            this.rs = this.prst.executeQuery();
            
            // 获取实体对象的流
            this.resultStream = EntityOperate.createEntities(this.rs, this.structure);

            this.command.setResultStream(this.resultStream); // 设置实体对象的流
            
        } catch (SQLException e) {
            throw e;
        } finally {
            Sql.close(new AutoCloseable[]{this.prst, this.rs});
        }
    }

    /**
     * 准备SQL语句
     */
    @Override
    public void prepare() throws Exception {
        
        switch(this.psType) { // 判断预编译类型
            case SQL:
                this.bySql();
                break;
                
            case CONDITION:
                this.byCondition();
                break;
                
            default: // 没有符合要求的预编译类型
                throw new Exception();
        }

    }

    @Override
    protected void bySql() throws SQLException {
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        // 设置参数
        if (this.args != null) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, this.args[i - 1]);
            }
        }
    }

    @Override
    protected void byCondition() throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM ");
        
        sql.append(this.table);
        
        if (this.condition != null) {
            sql.append(" WHERE ").append(this.condition);  // 设置条件
        }
        
        if (this.sort != null) {
            sql.append(" ORDER BY ").append(this.sort);  // 设置排序
        }
        
        this.SQL = sql.toString();
        
        this.prst = this.connection.prepareStatement(this.SQL);
        // 设置参数
        if (this.args != null) {
            for (int i = 1; i <= this.args.length; i ++) {
                this.prst.setObject(i, this.args[i - 1]);
            }
        }
        
    }

    @Override
    protected void byTarget() throws Exception {
        // Do not need to override.
    }

    @Override
    protected void byTargets() throws Exception {
        // Do not need to override.
    }
}
