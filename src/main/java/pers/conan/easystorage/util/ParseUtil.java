package pers.conan.easystorage.util;

import java.util.Collection;
import java.util.List;

import pers.conan.easystorage.parse.*;
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
     * 判断字符串是否是空白字符
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (isEmpty(str)) {  // 参数对象为空
            return true;
        }

        return isEmpty(str.trim());
    }

    /**
     * 编辑查询项
     * @param selections
     * @param table
     * @return
     */
    public static String editSelections(List<BaseModule> selections, String table) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < selections.size(); i ++) {
            BaseModule module = selections.get(i);

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

    /**
     * 编辑条件
     * @param condition 条件
     * @param conModules 条件数据模块集合
     * @return
     */
    public static String editCondition(Condition condition, List<ConditionModule> conModules) {

        if (isEmpty(condition)) {  // 条件为空
            return "";
        }

        if (isEmpty(condition.getConModule())) {  // 条件数据模块为空
            return "";
        }

        conModules.clear();  // 清空条件数据模块集合

        StringBuilder sb = new StringBuilder(" ( ");  // 条件开始

        ConditionModule condModule = condition.getConModule();  // 获取条件数据模块

        if (isBlank(condModule.getAlias()) == false) {
            sb.append(condModule.getAlias());
        } else {
            sb.append(condModule.getName());
        }

        switch (condModule.getConType()) {
            case EQUAL:
                sb.append(" = ? " );
                conModules.add(condModule);
                break;
            case MORE:
                sb.append(" > ? ");
                conModules.add(condModule);
                break;
            case MORE_OR_EQUAL:
                sb.append(" >= ? ");
                conModules.add(condModule);
                break;
            case LESS:
                sb.append(" < ? ");
                conModules.add(condModule);
                break;
            case LESS_OR_EQUAL:
                sb.append(" <= ? ");
                conModules.add(condModule);
                break;
            case NOT_EQUAL:
                sb.append(" <> ? ");
                conModules.add(condModule);
                break;
            case EMPTY:
                sb.append(" IS NULL ");
                break;  // 不需要放入条件数据模块集合
        }

        if (isEmpty(condition.getAndCondition()) == false) {  // 有且条件
            sb.append(" AND " + editCondition(condition.getAndCondition(), conModules));
         }

        if (isEmpty(condition.getOrCondition()) == false) {  // 有或条件
            sb.append(" OR " + editCondition(condition.getOrCondition(), conModules));
        }

        sb.append(" ) ");  // 条件结束

        return sb.toString();

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
