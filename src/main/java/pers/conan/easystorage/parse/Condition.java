package pers.conan.easystorage.parse;

import pers.conan.easystorage.util.ParseUtil;

/**
 * 类：条件(查询，更新，删除)
 * @author Conan Jordan
 */
public class Condition {

    /**
     * 条件数据模块
     */
    private ConditionModule conModule;

    /**
     * 构造方法
     */
    public Condition() {

    }

    /**
     * 构造方法
     * @param conModule
     */
    public Condition(ConditionModule conModule) {
        this.conModule = conModule;
    }

    public ConditionModule getConModule() {
        return conModule;
    }

    public void setConModule(ConditionModule conModule) {
        this.conModule = conModule;
    }

    /**
     * 且条件
     */
    private Condition andCondition;

    /**
     * 或条件
     */
    private Condition orCondition;

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
