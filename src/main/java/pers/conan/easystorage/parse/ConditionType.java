package pers.conan.easystorage.parse;

/**
 * 枚举：条件类型
 * @author Conan Jordan
 */
public enum ConditionType {

    /**
     * 等于
     */
    EQUAL,

    /**
     * 小于
     */
    LESS,

    /**
     * 小于或等于
     */
    LESS_OR_EQUAL,

    /**
     * 大于
     */
    MORE,

    /**
     * 大于或等于
     */
    MORE_OR_EQUAL,

    /**
     * 不等于
     */
    NOT_EQUAL,

    /**
     * 为空
     */
    EMPTY,

    /**
     * 存在于
     */
    EXIST,

    /**
     * 大于等于……且小于等于……
     */
    BETWEEN

}
