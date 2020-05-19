package pers.conan.easystorage.operate;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.CommonUtil;
import pers.conan.easystorage.util.Sql;


/**
 * 类：插入操作
 *
 * @author Conan
 */
public class InsertOperate extends PreCompile implements Operate {
    
    private static final Logger LOG = Logger.getLogger(InsertOperate.class);
    
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
        instance.args = command.getArgs();
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
        this.args = command.getArgs();
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
        
        switch(this.psType) { // 判断预编译类型
            case SQL:
                this.bySql();
                break;
                
            case TARGET:
                this.byTarget();
                break;
                
            case TARGETS:
                this.byTargets();
                break;
                
            default: // 没有预编译类型或条件预编译类型
                throw new Exception();
        }
    }

    /**
     * 执行SQL操作
     * @throws SQLException 
     */
    @Override
    public void operate() throws SQLException {
        try {
            if (this.psType != PreparedStatementType.TARGETS) { // 不需要批量处理
                this.resultCount = this.prst.executeUpdate(); // 获取成功执行的记录数 
            } else { // 需要批量处理
                this.resultCount = Arrays.stream(this.prst.executeBatch())
                                .reduce(0, (acc, element) -> acc + element); // 获取成功执行的记录数       
            }
            
            this.command.setResultCount(this.resultCount); // 设置成功执行的记录数
        } catch (SQLException e) {
            throw e;
        } finally {
            // 释放数据库资源
            Sql.close(new AutoCloseable[] {this.prst});
        }
    }

    /**
     * 基于SQL语句预编译
     * @throws SQLException 
     */
    @Override
    protected void bySql() throws SQLException {
        
        LOG.debug("The SQL below will be excuted:" + this.SQL);
        this.prst = this.connection.prepareStatement(this.SQL); // 预编译
        
        // 设置参数
        if (CommonUtil.isNotEmpty(this.args)) {
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
        
        LOG.debug("The SQL below will be excuted:" + this.SQL);
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
        
        LOG.debug("The SQL below will be excuted:" + this.SQL);
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
