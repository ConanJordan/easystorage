package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Column;
import pers.conan.easystorage.annotation.PrimaryKey;
import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.util.CommonUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * 类：解析实体
 *
 * @author Conan
 */
public class EntityParse {

    // 获取目标实体类的所有属性的流
    public static Stream<Field> getAllFields(Class<? extends Structure> structure) {
        return Stream.of(structure.getDeclaredFields());  // 获取所有属性(private 和 public)
    }

    // 获取目标实体类的所有属性名称的流
    public static Stream<String> getAllFiledNames(Stream<Field> fields) {
        return fields.map(field -> field.getName());
    }

    // 获取目标实体类的属性的Column注解的值
    public static String getFieldColumn(Field field) {
        return field.getAnnotation(Column.class).value();
    }

    // 获取属性的set方法
    public static Method getSetMethod(Class<? extends Structure> structure, Field field) throws NoSuchMethodException {
        // 获取set方法的名称(一般来说是set + 大写的属性名称首字母 + 属性名称的剩余字母)
        String fieldName = field.getName();
        String predictName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        // 获取属性类型
        Class<?> type = field.getType();

        // 获取属性的set方法
        Method method = structure.getMethod(predictName, type);

        return method;
    }
    
    // 获取属性的get方法
    public static Method getGetMethod(Class<? extends Structure> structure, Field field) throws NoSuchMethodException {
        // 获取set方法的名称(一般来说是get + 大写的属性名称首字母 + 属性名称的剩余字母)
        String fieldName = field.getName();
        String predictName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        // 获取属性的get方法
        Method method = structure.getMethod(predictName);

        return method;
    }
    
    // 获取目标实体类的主键的字段名称的流
    public static Stream<String> getPkColumns(Class<? extends Structure> structure) {
        return Stream.of(structure.getDeclaredFields()) // 获取所有属性
               .filter(field -> !CommonUtil.isEmpty(field.getAnnotation(PrimaryKey.class))) // 过滤出作主键的属性
               .map(field -> field.getAnnotation(Column.class).value()); // 映射成字段名称的流
    }
    
    // 获取目标实体类的作主键的属性的流
    public static Stream<Field> getPkFields(Class<? extends Structure> structure) {
        return Stream.of(structure.getDeclaredFields()) // 获取所有属性
                .filter(field -> !CommonUtil.isEmpty(field.getAnnotation(PrimaryKey.class))); // 过滤出作主键的属性
    }

    // 获取目标属性的值
    public static Object getFieldValue(Class<? extends Structure> structure, Field field, Structure instance) 
            throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method getMethod = getGetMethod(structure, field); // 获取目标属性的get方法
        return getMethod.invoke(instance); // 返回get方法获取到的值
    }
}
