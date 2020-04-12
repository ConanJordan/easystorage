package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;
import pers.conan.easystorage.util.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class UpdateParse extends BaseParse {

    /**
     * 更新表的名称
     */
    private String table;

    /**
     * 更新数据模块集合
     */
    private List<BaseModule> updates = new ArrayList<BaseModule>();

    /**
     * 更新条件
     */
    private Condition updateCondition;

    /**
     * 条件数据模块集合
     */
    private List<ConditionModule> conModules = new ArrayList<ConditionModule>();

    /**
     * 构造方法
     * @param table
     * @param updateCondition
     * @param conModules
     */
    public UpdateParse(String table, Condition updateCondition, List<ConditionModule> conModules) {
        this.table = table;
        this.updateCondition = updateCondition;
        this.conModules = conModules;
    }

    /**
     * 构造方法
     */
    public UpdateParse() {
    }

    /**
     * 解析(生成SQL语句)
     *
     * @return
     */
    @Override
    public String parse() throws DisableToParseException {

        if (ParseUtil.isBlank(this.table)) {  // 没有要更新的表
            throw new DisableToParseException();
        }
        if (ParseUtil.isEmpty(this.updates)) {  // 没有要更新的数据项
            throw new DisableToParseException();
        }

        StringBuilder SQL = new StringBuilder("UPDATE ");  // 开始编辑SQL语句

        SQL.append(this.table);  // 表名

        SQL.append(ParseUtil.editUpdates(this.updates));  // 更新项目

        SQL.append(ParseUtil.editCondition(this.updateCondition, this.conModules)); // 更新条件

        this.sql = SQL.toString();  // SQL语句编辑完成(可用于PreparedStatement)

        return this.sql;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<BaseModule> getUpdates() {
        return updates;
    }

    public void setUpdates(List<BaseModule> updates) {
        this.updates = updates;
    }

    public Condition getUpdateCondition() {
        return updateCondition;
    }

    public void setUpdateCondition(Condition updateCondition) {
        this.updateCondition = updateCondition;
    }

    public List<ConditionModule> getConModules() {
        return conModules;
    }
}
