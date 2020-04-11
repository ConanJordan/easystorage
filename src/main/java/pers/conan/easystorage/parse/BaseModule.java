package pers.conan.easystorage.parse;

/**
 * 类：基础数据模块
 * @author Conan Jordan
 */
public class BaseModule {

    /**
     * 数据模块名称
     */
    protected String name;

    /**
     * 数据模块的值
     */
    protected Object value;

    /**
     * 数据模块的别名
     */
    protected String alias;

    /**
     * 数据类型
     */
    protected DataType type;

    /**
     * 构造方法:用于初始化通用数据模块
     * @param name
     * @param value
     * @param type
     * @param alias
     */
    public BaseModule(String name, Object value, DataType type, String alias) {
        this.name = name;
        this.value = value;
        this.alias = alias;
        this.type = type;
    }

    /**
     * 构造方法:用于初始化通用数据模块
     */
    public BaseModule() {
    }

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
}
