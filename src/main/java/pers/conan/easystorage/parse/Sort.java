package pers.conan.easystorage.parse;

import java.util.ArrayList;
import java.util.List;

/**
 * 类：查询结果集排序
 * @author Conan Jordan
 */
public class Sort {

    /**
     * 排序集合
     */
    List<Module> sorts = new ArrayList<Module>();


    /**
     * 添加排序方式
     * @param name
     * @param alias
     * @param asc
     */
    public void addSort(String name, String alias, boolean asc) {
        this.sorts.add(new Module(name, asc, alias));
    }

    public List<Module> getSorts() {
        return sorts;
    }

    public void setSorts(List<Module> sorts) {
        this.sorts = sorts;
    }

    /**
     * 构造方法
     * @param sorts
     */
    public Sort(List<Module> sorts) {
        this.sorts = sorts;
    }

    /**
     * 构造方法
     */
    public Sort() {
    }
}
