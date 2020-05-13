package pers.conan.easystorage.operate;

import java.util.Objects;
import java.util.function.Function;

/**
 * 类：查询项
 * @param <T>
 * @param <R>
 *
 * @author Conan
 */
public class Selection<T, R> {

    /**
     * 表名
     */
    private String table;

    /**
     * 字段名
     */
    private String column;

    /**
     * 别名
     */
    private String alias;

    /**
     * 查询值
     */
    private T before;

    /**
     * 返回值
     */
    private R after;

    /**
     * 编辑函数
     */
    private Function<T, R> editFun;

    /**
     * 构造方法
     * @param table
     * @param column
     * @param alias
     * @param editFun
     */
    public Selection(String table, String column, String alias, Function<T, R> editFun) {

        Objects.requireNonNull(table);
        this.table = table;

        Objects.requireNonNull(column);
        this.column = column;

        if (alias != null) {
            this.alias = alias;
        } else {
            this.alias = this.column;
        }

        this.editFun = editFun;
    }

    /**
     * 编辑查询值
     */
    public void edit() {
        if (editFun != null) {
            this.after = this.editFun.apply(this.before);

        }
    }

    public T getBefore() {
        return before;
    }

    public void setBefore(T before) {
        this.before = before;
    }

    public R getAfter() {
        return after;
    }

    public void setAfter(R after) {
        this.after = after;
    }
}
