package pers.conan.easystorage.operate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.database.ClientCommand;

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
    private Object[] args;
    private Structure target;
    private Collection<Structure> targets;
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
        instance.target = command.getTarget();
        instance.targets = command.getTargets();
        instance.table = command.getTable();
        instance.args = command.getArgs();
        instance.command = command;

        // 设置属性

        return instance; // 返回实例对象
    }
    
    /**
     * 准备SQL语句
     */
    @Override
    public void prepare() throws Exception {

    }

    /**
     * 执行SQL操作
     */
    @Override
    public void operate() {

    }
}
