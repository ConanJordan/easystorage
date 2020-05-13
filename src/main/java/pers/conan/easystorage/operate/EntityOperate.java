package pers.conan.easystorage.operate;

import pers.conan.easystorage.annotation.Structure;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 类：操作实体
 *
 * @author Conan
 */
public class EntityOperate {

    // 生成实体化对象的流
    public static Stream<? extends Structure> createEntities(
            ResultSet resultSet,
            Class<? extends Structure> structure) throws SQLException {

        Set<String> columns = getColumns(resultSet); // 获取要设置的字段

        return Stream.of(resultSet)
                .parallel()
                .map(item ->
                {
                    try {
                        return createEntity(resultSet, columns, structure);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    /**
     * 获取字段名的集合
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static Set<String> getColumns(ResultSet resultSet) throws SQLException {
        Set<String> columns = new HashSet<String>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i ++) {
            columns.add(metaData.getColumnName(i));
        }
        return columns;
    }

    /**
     * 生成实体对象
     * @param resultSet
     * @param columns
     * @param structure
     * @return
     */
    public static Structure createEntity(
            ResultSet resultSet,
            Set<String> columns,
            Class<? extends Structure>structure Class<? extends Structure> structure)
            throws NoSuchMethodException {

        // 生成实体对象
        Object instance = structure.getConstructor().instance;

        EntityParse.getAllFields(structure) // 获取所有属性
                .filter(field -> columns.contains(EntityParse.getFieldColumn(field))) // 过滤要设置的属性
                .forEach(field -> {
                    try {
                        editEntity((Structure) instance, field, resultSet); // 设置属性
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        return (Structure) instance; // 返回实体对象

    }

    /**
     * 设置实体对象的属性
     * @param instance
     * @param field
     * @param resultSet
     */
    public static void editEntity(
            Structure instance, Field field, ResultSet resultSet)
            throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {

        // 获取set方法
        Method setMethod = EntityParse.getSetMethod(instance.getClass(), field);

        // 调用set方法，设置属性的值
        setMethod.invoke(instance, resultSet.getObject(EntityParse.getFieldColumn(field)));
    }

}
