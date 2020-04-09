package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;
import pers.conan.easystorage.util.ParseUtil;

public class DeleteParse extends BaseParse {

    /**
     * 表名
     */
    private String table;

    /**
     * 删除条件
     */
    private Condition condition;

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

        StringBuilder sb = new StringBuilder("DELETE FROM " + this.table + " WHERE ");

        sb.append(ParseUtil.editCondition(this.condition));

        this.sql = sb.toString();  // 生成对应的SQL语句

        return this.sql;
    }
}
