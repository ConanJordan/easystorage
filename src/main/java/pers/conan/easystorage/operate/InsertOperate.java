package pers.conan.easystorage.operate;

import pers.conan.easystorage.database.ClientCommand;


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
        instance.condition = command.getCondition();
        instance.target = command.getTarget();
        instance.targets = command.getTargets();
        instance.table = command.getTable();
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

        // TODO
        
    }

    /**
     * 执行SQL操作
     */
    @Override
    public void operate() {

    }

    @Override
    protected void bySql() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void byCondition() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void byTarget() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void byTargets() throws Exception {
        // TODO Auto-generated method stub
        
    }
}
