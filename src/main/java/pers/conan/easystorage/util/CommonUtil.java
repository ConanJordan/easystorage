package pers.conan.easystorage.util;

import java.util.Collection;

/**
 * 共同工具类
 * 
 * @author Conan
 */
public class CommonUtil {
    
    /**
     * 判断对象是否为空
     * 当对象是集合或数组时，只需判断内部是否含有元素
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        
        if (obj instanceof Collection) {
            return ((Collection<?>)obj).isEmpty();
        }
        
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        
        return "".equals(obj.toString());
    }
    
    /**
     * 判断对象是否不为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
