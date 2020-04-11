package pers.conan.easystorage.parse;

/**
 * 类：数据模块
 * @author Conan Jordan
 */
public class Module {

    /**
     * 名称
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 数据值
     */
    private Object value;

    /**
     * 数据类型
     */
    private DataType type;

    /**
     * 条件类型
     */
    private ConditionType conType;

    /**
     * 升序
     */
    private boolean asc = true;

    /**
     * 构造方法:用于Insert语句的插入项
     * @param name
     * @param value
     * @param type
     * @param alias
     */
    public Module(String name, Object value, DataType type, String alias) {
        this.name = name;
        this.alias = alias;
        this.value = value;
        this.type = type;
    }

    /**
     * 构造方法:用于Select语句的排序项
     * @param name
     * @param asc
     * @param alias
     */
    public Module(String name, boolean asc, String alias) {
        this.name = name;
        this.alias = alias;
        this.asc = asc;
    }

    /**
     * 构造方法:用于Where后的条件项
     * @param name
     * @param value
     * @param type
     * @param conType
     * @param alias
     */
    public Module(String name, Object value, DataType type, ConditionType conType, String alias) {
        this.name = name;
        this.alias = alias;
        this.value = value;
        this.type = type;
        this.conType = conType;
    }

    /**
     * 构造方法:初始化通用数据模块
     * @param name
     * @param alias
     */
    public Module(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public ConditionType getConType() {
        return conType;
    }

    public void setConType(ConditionType conType) {
        this.conType = conType;
    }
}
