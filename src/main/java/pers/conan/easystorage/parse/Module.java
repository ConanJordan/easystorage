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

    public Module(String name, String alias, Object value, DataType type) {
        this.name = name;
        this.alias = alias;
        this.value = value;
        this.type = type;
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
}
