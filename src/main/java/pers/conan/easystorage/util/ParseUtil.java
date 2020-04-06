package pers.conan.easystorage.util;

import java.util.Collection;
import pers.conan.easystorage.parse.Module;

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
     * @param module
     * @param table
     * @param commaPrefix
     * @return
     */
    public static String editSelections(Module module, String table, boolean commaPrefix) {
        StringBuilder selection = new StringBuilder();
        
        selection.append(commaPrefix ? ", " : " ");  // 逗号
        selection.append(ParseUtil.isEmpty(table) ? "" : (table + "."));  // 表名
        selection.append(module.getName());  // 字段名
        selection.append(ParseUtil.isEmpty(module.getName()) ? "" : (" As " + module.getAlias()));  // 别名

        return selection.toString();
    }

}
