package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;
import pers.conan.easystorage.util.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class DeleteParse extends BaseParse {

    /**
     * 表名
     */
    private String table;

    /**
     * 删除条件
     */
    private Condition condition;

    /**
     * 删除条件的数据模块集合
     */
    private List<ConditionModule> conModules = new ArrayList<ConditionModule>();

    public DeleteParse(String table, Condition condition) {
        this.table = table;
        this.condition = condition;
    }

    public DeleteParse() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String parse() throws DisableToParseException {

        if (ParseUtil.isEmpty(this.table)) {  // 表名为空
            throw new DisableToParseException();
        }

        if (ParseUtil.isEmpty(this.condition)) {  // 条件为空
            throw new DisableToParseException();
        }

        StringBuilder SQL = new StringBuilder("DELETE FROM " + this.table + " WHERE ");  // 开始编辑SQL语句

        SQL.append(ParseUtil.editCondition(this.condition, this.conModules));

        this.sql = SQL.toString();  // SQL语句编辑完成

        return this.sql;
    }
}
