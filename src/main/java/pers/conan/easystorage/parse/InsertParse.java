package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;
import pers.conan.easystorage.util.ParseUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类：增加记录的解析器
 * @author Conan Jordan
 */
public class InsertParse extends BaseParse {

    /**
     * 表名
     * 新增数据的表
     */
    private String table;

    /**
     * 新增数据的模块集合
     */
    private List<BaseModule> insertions = new ArrayList<BaseModule>();

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<BaseModule> getInsertions() {
        return insertions;
    }

    public void setInsertions(List<BaseModule> insertions) {
        this.insertions = insertions;
    }

    /**
     * 添加数据模块
     * @param insertion
     */
    public void add(BaseModule insertion) {
        this.insertions.add(insertion);
    }

    /**
     * 解析(生成SQL语句)
     *
     * @return
     */
    @Override
    public String parse() throws DisableToParseException {

        if (ParseUtil.isEmpty(this.table) || ParseUtil.isEmpty(this.insertions)) {
            // 没有表名或插入项
            throw new DisableToParseException();
        }

        // 获取新增记录的列名和数据
        //getInsertColAndVal(this.cols, this.values);

        StringBuilder SQL = new StringBuilder("INSERT INTO ");  // 开始编辑SQL语句

        SQL.append(this.table);  // 表名

        StringBuilder cols = new StringBuilder(" ( ");  // 插入记录的字段
        StringBuilder values = new StringBuilder(" VALUES ( ");  // 插入记录的数据

        for (int i = 0; i < this.insertions.size(); i ++) {
            if (i == 0) {
                cols.append(this.insertions.get(i).getName());
                values.append(" ? ");
            } else {
                cols.append(", " + this.insertions.get(i).getName());
                values.append(", ? ");
            }
        }

        cols.append(" ) ");
        values.append(" ) ");

        SQL.append(cols.toString() + values.toString());

        this.sql = SQL.toString();  // SQL语句编辑完成(可用于PreparedStatement)

        return this.sql;
    }


}
