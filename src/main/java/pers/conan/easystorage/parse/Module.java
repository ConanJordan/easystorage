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
     * 升序
     */
    private boolean asc = true;

    /**
     * 构造方法
     * @param name
     * @param alias
     * @param value
     * @param type
     */
    public Module(String name, String alias, Object value, DataType type) {
        this.name = name;
        this.alias = alias;
        this.value = value;
        this.type = type;
    }

    /**
     * 构造方法
     * @param name
     * @param alias
     * @param asc
     */
    public Module(String name, String alias, boolean asc) {
        this.name = name;
        this.alias = alias;
        this.asc = asc;
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
}
