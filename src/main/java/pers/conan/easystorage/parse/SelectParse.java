package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;
import pers.conan.easystorage.util.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectParse extends BaseParse {

    /**
     * 查询表
     */
    private String table;

    /**
     * 查询项
     */
    private List<BaseModule> selections = new ArrayList<BaseModule>();

    /**
     * 查询条件
     */
    private Condition condition;

    /**
     * 查询结果排序
     */
    private Sort sort;

    /**
     * 条件数据模块集合
     */
    private List<ConditionModule> conModules = new ArrayList<ConditionModule>();

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<BaseModule> getSelections() {
        return selections;
    }

    public void setSelections(List<BaseModule> selections) {
        this.selections = selections;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public SelectParse(String table, List<BaseModule> selections, Condition condition, Sort sort) {
        this.table = table;
        this.selections = selections;
        this.condition = condition;
        this.sort = sort;
    }

    public SelectParse() {
    }

    /**
     * 解析(生成SQL语句)
     *
     * @return
     */
    @Override
    public String parse() throws DisableToParseException {

        if (ParseUtil.isEmpty(this.table)) {  // 没有要查询的表
            throw new DisableToParseException();
        }

        if (ParseUtil.isEmpty(this.selections)) {  // 没有要查询的项目
            throw new DisableToParseException();
        }

        StringBuilder SQL = new StringBuilder("SELECT ");

        SQL.append(ParseUtil.editSelections(this.selections, this.table));  // 编辑查询项

        SQL.append(" FROM " + this.table);  // 查询的表

        if (ParseUtil.isEmpty(this.condition) == false) {  // 有查询条件
            SQL.append(" WHERE ");
            SQL.append(ParseUtil.editCondition(this.condition, this.conModules));  // 添加查询条件
        }

        if (ParseUtil.isEmpty(this.sort)) {  // 有排序条件
            SQL.append(" SORTED BY ");
            SQL.append(ParseUtil.editSort(this.sort));  // 添加排序条件
        }

        this.sql = SQL.toString();  // SQL语句编辑完成(可用于PreparedStatement)

        return this.sql;
    }
}
