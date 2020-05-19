package pers.conan.easystorage.operate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.CommonUtil;


/**
 * 类：插入操作
 *
 * @author Conan
 */
public class InsertOperate extends PreCompile implements Operate {
    
    /**
     * 有序列号的属性集合
     */
    private List<Field> seqFields = new ArrayList<>();
    
    /**
     * 自动递增的属性集合
     */
    private List<Field> autoFields = new ArrayList<>();
    
    /**
     * 其他属性集合
     */
    private List<Field> otherFields = new ArrayList<>();

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
        // 解析目标类的属性结构
        EntityParse.parseEntityClass(this.structure, this.seqFields, this.autoFields, this.otherFields);
        
        StringBuilder sql1 = new StringBuilder("INSERT INTO "); // SQL语句前半部分
        sql1.append(this.table); // 表名
        sql1.append(" ( ");
        
        StringBuilder sql2 = new StringBuilder(" VALUES ( "); // SQL语句后半部分
        
        // 给SQL语句添加序列号的字段
        for (int i = 0; i < this.seqFields.size(); i ++) {
            sql1.append(EntityParse.getFieldColumn(seqFields.get(i)) + ", ");
            sql2.append(EntityParse.getSequence(seqFields.get(i)) + ".NEXTVAL, ");
        }
        
        // 给SQL语句添加其他字段
        for (int i = 0; i < this.otherFields.size(); i ++) {
            sql1.append(EntityParse.getFieldColumn(otherFields.get(i)) + ", ");
            sql2.append("?, ");
        }
        
        // 整合SQL语句
        this.SQL = 
                sql1.toString().substring(0, sql1.length() - 2)
                + " ) "
                + sql2.toString().substring(0, sql2.length() - 2)
                + " ) ";
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        for (int i = 1; i <= this.otherFields.size(); i ++) {
            this.prst.setObject(i, EntityParse.getFieldValue(this.structure, otherFields.get(i - 1), this.target));
        }
        
    }

    /**
     * 基于目标对象集合预编译
     */
    @Override
    protected void byTargets() throws Exception {
        // 解析目标类的属性结构
        EntityParse.parseEntityClass(this.structure, this.seqFields, this.autoFields, this.otherFields);
        
        StringBuilder sql1 = new StringBuilder("INSERT INTO "); // SQL语句前半部分
        sql1.append(this.table); // 表名
        sql1.append(" ( ");
        
        StringBuilder sql2 = new StringBuilder(" VALUES ( "); // SQL语句后半部分
        
        // 给SQL语句添加序列号的字段
        for (int i = 0; i < this.seqFields.size(); i ++) {
            sql1.append(EntityParse.getFieldColumn(seqFields.get(i)) + ", ");
            sql2.append(EntityParse.getSequence(seqFields.get(i)) + ".NEXTVAL, ");
        }
        
        // 给SQL语句添加其他字段
        for (int i = 0; i < this.otherFields.size(); i ++) {
            sql1.append(EntityParse.getFieldColumn(otherFields.get(i)) + ", ");
            sql2.append("?, ");
        }
        
        // 整合SQL语句
        this.SQL = 
                sql1.toString().substring(0, sql1.length() - 2)
                + " ) "
                + sql2.toString().substring(0, sql2.length() - 2)
                + " ) ";
        
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        this.targets.stream()
            .forEach(item -> {
                for (int i = 1; i <= this.otherFields.size(); i ++) {
                    try {
                        this.prst.setObject(i, EntityParse.getFieldValue(this.structure, otherFields.get(i - 1), this.target));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                
                try {
                    this.prst.addBatch(); // 批量预编译
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        
        
        
        
    }
}
