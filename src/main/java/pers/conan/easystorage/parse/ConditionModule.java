package pers.conan.easystorage.parse;

/**
 * 类：条件数据模块
 * @author Conan Jordan
 */
public class ConditionModule extends BaseModule {

    /**
     * 条件类型
     */
    private ConditionType conType = ConditionType.EQUAL;

    /**
     * 构造方法：用于初始化条件数据模块
     * @param name
     * @param value
     * @param alias
     * @param type
     * @param conType
     */
    public ConditionModule(String name, Object value, String alias, DataType type, ConditionType conType) {
        super(name, value, type, alias);
        this.conType = conType;
    }

    /**
     * 构造方法：用于初始化条件数据模块
     */
    public ConditionModule() {
        super();
    }

    public ConditionType getConType() {
        return conType;
    }

    public void setConType(ConditionType conType) {
        this.conType = conType;
    }
}
