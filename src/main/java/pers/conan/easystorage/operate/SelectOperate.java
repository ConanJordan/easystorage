package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * 类：查询操作
 */
public class SelectOperate implements Operate {

    private ClientCommand command;
    private Connection connection = null;
    private PreparedStatement prst = null;
    private ResultSet rs = null;
    private String SQL = null;
    private String table = null;
    private String condition = null;
    private String sort = null;
    private Object[] args = null;
    private Class<? extends  Structure> structure;
    private Stream<? extends Structure> resultStream;

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

            this.command.setResultStream(this.resultStream);
            
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
        if (this.SQL == null && this.table == null) {
            throw new Exception("The sql or the table should not be empty when attempting to query from it.");
        }
        
        if (this.SQL != null) {
            this.prst = this.connection.prepareStatement(this.SQL);
            // 设置参数
            if (this.args != null) {
                for (int i = 1; i <= this.args.length; i ++) {
                    this.prst.setObject(i, this.args[i - 1]);
                }
            }
        } else if (this.table != null) {
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

    }
}
