package pers.conan.easystorage.parse;

import pers.conan.easystorage.util.ParseUtil;

/**
 * 类：条件(查询，更新，删除)
 * @author Conan Jordan
 */
public class Condition {

    /**
     * 名称(别名)
     */
    private String name;

    /**
     * 值
     */
    private Object value;

    /**
     * 表名
     */
    private String table;

    /**
     * 字段名
     */
    private String column;

    /**
     * 条件类型
     */
    private ConditionType type;

    /**
     * 且条件
     */
    private Condition andCondition;

    /**
     * 或条件
     */
    private Condition orCondition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ConditionType getType() {
        return type;
    }

    public void setType(ConditionType type) {
        this.type = type;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * 构造方法
     * @param name
     * @param value
     * @param type
     */
    public Condition(String name, Object value, ConditionType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    /**
     * 构造方法
     * @param table
     * @param column
     * @param value
     * @param type
     */
    public Condition(String table, String column, Object value, ConditionType type) {
        this.value = value;
        this.table = table;
        this.column = column;
        this.type = type;
    }

    /**
     * 构造方法
     */
    public Condition() {

    }

    public Condition getAndCondition() {
        return andCondition;
    }

    public Condition getOrCondition() {
        return orCondition;
    }

    /**
     * 添加且条件
     * @param andCondition
     */
    public void addAnd(Condition andCondition) {
        if (ParseUtil.isEmpty(this.andCondition)) {  // 没有且条件
            this.andCondition = andCondition;
        } else {  // 有且条件
            this.andCondition.addAnd(andCondition);
        }
    }

    /**
     * 添加或条件
     * @param orCondition
     */
    public void addOr(Condition orCondition) {
        if (ParseUtil.isEmpty(this.orCondition)) {  // 没有或条件
            this.orCondition = orCondition;
        } else {  // 有或条件
            this.orCondition.addAnd(orCondition);
        }
    }

}
