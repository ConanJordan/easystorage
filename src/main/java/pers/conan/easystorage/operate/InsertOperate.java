package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.database.ClientCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

/**
 * 类：插入操作
 *
 * @author Conan
 */
public class InsertOperate implements Operate {

    private ClientCommand command;
    private Connection connection = null;
    private PreparedStatement prst = null;
    private ResultSet rs = null;
    private String SQL = null;
    private String table = null;
    private String seq;
    private String condition = null;
    private Object[] args = null;
    private Class<? extends Structure> structure;
    private Structure target;
    private Collection<Structure> targets;

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
        instance.condition = command.getCondition();
        instance.target = command.getTarget();
        instance.targets = command.getTargets();
        instance.table = command.getTable();
        instance.seq = command.getSeq();
        instance.command = command;

        // 设置属性

        return instance; // 返回实例对象
    }

    /**
     * 准备SQL语句
     */
    @Override
    public void prepare() throws Exception {
        if (this.table == null) {
            throw new Exception("The table should not be empty when attempting to insert into it.");
        }

        
    }

    /**
     * 执行SQL操作
     */
    @Override
    public void operate() {

    }
}
