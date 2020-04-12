package pers.conan.easystorage.util;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.parse.BaseModule;
import pers.conan.easystorage.parse.DataType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类：解析结构体的工具类
 */
public class StructureParseUtil {

    public static final String TABLE = "TABLE";

    public static final String MOUDLES = "MODULES";

    /**
     * 解析结构体
     * @param structure
     * @return
     */
    public static Map<String, Object> parseStructure(Structure structure) throws IllegalAccessException {

        Map<String, Object> result = new HashMap<String, Object>();  // 初始化返回值

        List<BaseModule> modules = new ArrayList<BaseModule>();  // 结构体实例化对象的数据模块集合

        Class<?> targetCls = structure.getClass();  // 获取结构体实例化对象的类对象

        Field[] fields = targetCls.getDeclaredFields();  // 获取类对象的所有属性

        for (int i = 0; i < fields.length; i ++) {

            Field field = fields[i];  // 获取属性对象
            field.setAccessible(true);  // 更改属性对象为可访问模式

            modules.add(createModule(field, structure));
        }

        result.put(MOUDLES, modules);

        // TODO 表名

        return result;

    }

    public static List<Map<String, Object>> parseStructures() {
        return null;
    }

    /**
     * 创建数据模块对象
     * @param field
     * @param structure
     * @return
     * @throws IllegalAccessException
     */
    private static BaseModule createModule(Field field, Structure structure) throws IllegalAccessException {
        BaseModule module = new BaseModule();  // 初始化返回值

        module.setName(field.getName());  // 设置数据模块的名称
        module.setValue(field.get(structure));  // 设置数据模块的值
        module.setType(getDataType(field.getClass()));// 设置数据模块的数据类型

        Column column = field.getAnnotation(Column.class);  // 获取注解对象(Column)

        if (! ParseUtil.isEmpty(column)) {  // 有注解对象
            module.setName(column.alias());

            if (! ParseUtil.isBlank(column.name())) {
                module.setName(column.name());
            }

        }

        return module;
    }

    /**
     * 获取数据模块的对象类型
     * @param cls
     * @return
     */
    private static DataType getDataType(Class<?> cls) {
        if (cls.equals(Integer.class)) {
            return DataType.NUMBER;
        }
        if (cls.equals(Double.class)) {
            return DataType.NUMBER;
        }
        if (cls.equals(Float.class)) {
            return DataType.NUMBER;
        }
        if (cls.equals(String.class) || cls.equals(CharSequence.class)) {
            return DataType.TEXT;
        }
        if (cls.equals(java.sql.Date.class) || cls.equals(java.util.Date.class)) {
            return DataType.DATE;
        }
        return DataType.OTHER;
    }

}
