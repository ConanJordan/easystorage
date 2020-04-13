package pers.conan.easystorage.util;

/**
 * 类：字符串工具
 * @author Conan Jordan
 */
public class StringUtil {

    /**
     * 把驼峰命名法命名的字符串改用下划线命名法命名
     * @param str 原字符串
     * @param isUpperCase  // 大小写
     * @return
     */
    public static String toUnderScore(String str, boolean isUpperCase) {
        if (ParseUtil.isBlank(str)) {
            return str;  // 空字符串直接返回
        }

        StringBuilder result = new StringBuilder();
        boolean upper = false;

        for (int i = 0; i < str.length(); i ++) {
            String s = str.substring(i, i + 1);  // 获取字符
            if (i == 0) {
                result.append(caseSwitch(s, isUpperCase));  // 首字母直接转换
                continue;
            }

            if (! s.equals(s.toUpperCase())) {  // 不是驼峰节点
                result.append(caseSwitch(s, isUpperCase));
            } else {  // 可能是驼峰节点
                if (s.equals(s.toLowerCase())) {  // 不是驼峰节点
                    result.append(caseSwitch(s, isUpperCase));
                } else {  // 是驼峰节点
                    result.append(caseSwitch("_" + s, isUpperCase));  // 节点前面加下划线
                }
            }
        }

        return result.toString();
    }

    /**
     * 把驼峰命名法命名的字符串改用下划线命名法命名
     * @param str 原字符串
     * @return
     */
    public static String toUnderScore(String str) {
        return toUnderScore(str, true);  // 默认大写字母下划线命名
    }

    /**
     * 字符串大小写转换
     * @param str
     * @param isUpperCase
     * @return
     */
    private static String caseSwitch(String str, boolean isUpperCase) {
        if (ParseUtil.isBlank(str)) {
            return str;  // 空字符串直接返回
        }
        if (isUpperCase) {
            return str.toUpperCase();  // 大写转换
        }
        return str.toLowerCase();  // 小写转换
    }
    
}
