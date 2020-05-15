package pers.conan.easystorage.util;

import java.util.Collection;

/**
 * 共同工具类
 * 
 * @author Conan
 */
public class CommonUtil {
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        
        if (obj instanceof Collection) {
            return ((Collection<?>)obj).isEmpty();
        }
        
        return "".equals(obj.toString());
    }
}
