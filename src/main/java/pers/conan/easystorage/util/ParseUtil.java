package pers.conan.easystorage.util;

import java.util.Collection;

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

}
