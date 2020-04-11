package pers.conan.easystorage.parse;

/**
 * 类：排序数据模块
 * @author Conan Jordan
 */
public class SortModule extends BaseModule {

    /**
     * 升序
     */
    private boolean asc;

    /**
     * 构造方法：用于初始化排序数据模块
     * @param name
     * @param asc
     * @param alias
     */
    public SortModule(String name, boolean asc, String alias) {
        super(name, null, null, alias);
        this.asc = asc;
    }

    /**
     * 构造方法：用于初始化排序数据模块
     */
    public SortModule() {
        super();
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }
}
