package pers.conan.easystorage.util;

import java.util.Collection;
import java.util.List;

import pers.conan.easystorage.parse.Condition;
import pers.conan.easystorage.parse.Module;
import pers.conan.easystorage.parse.Sort;

/**
 * 类：解析工具
 */
public class ParseUtil {

    /**
     * 判断参数对象是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {  // 参数对象为空
            return true;
        }

        if (obj instanceof Collection) {  // 参数对象是集合的实例
            return ((Collection) obj).isEmpty();
        }

        return "".equals(obj.toString());
    }

    /**
     * 编辑查询项
     * @param selections
     * @param table
     * @return
     */
    public static String editSelections(List<Module> selections, String table) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < selections.size(); i ++) {
            Module module = selections.get(i);

            if (i == 0) {  // 没有逗号
                sb.append(ParseUtil.isEmpty(table) ? "" : (table + "."));  // 表名
                sb.append(module.getName());  // 字段名
                sb.append(ParseUtil.isEmpty(module.getName()) ? "" : (" AS " + module.getAlias()));  // 别名
            } else {
                sb.append(" , " + (ParseUtil.isEmpty(table) ? "" : (table + ".")));  // 表名
                sb.append(module.getName());  // 字段名
                sb.append(ParseUtil.isEmpty(module.getName()) ? "" : (" AS " + module.getAlias()));  // 别名
            }
        }

        return sb.toString();
    }

    // 编辑查询条件
    public static String editConditons(Condition condtion, boolean commaPrefix) {
        StringBuilder sb = new StringBuilder();

        sb.append(commaPrefix ? ", " : " ");  // 逗号

        // TODO
        return null;

    }

    // 编辑排序条件
    public static String editSort(Sort sort) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sort.getSorts().size(); i ++) {

            Module module = sort.getSorts().get(i);

            if (i == 0) {  // 没有逗号
                if (ParseUtil.isEmpty(module.getAlias())) {  // 有别名，别名优先
                    sb.append(module.getAlias() + (module.isAsc() ? " ASC " : " DESC "));
                } else {  // 没有别名
                    sb.append(module.getName() + (module.isAsc() ? " ASC " : " DESC "));
                }
            } else {  // 有逗号
                if (ParseUtil.isEmpty(module.getAlias())) {  // 有别名，别名优先
                    sb.append(" , " + module.getAlias() + (module.isAsc() ? " ASC " : " DESC "));
                } else {  // 没有别名
                    sb.append(" , " + module.getName() + (module.isAsc() ? " ASC " : " DESC "));
                }
            }
        }

        return sb.toString();
    }

}
