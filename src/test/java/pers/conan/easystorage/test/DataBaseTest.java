package pers.conan.easystorage.test;

import pers.conan.easystorage.util.SqlUtil;

import java.sql.*;

public class DataBaseTest {

    public static void main(String[] args) {

        Connection connection = createConnection();

        PreparedStatement prst1 = null;
        PreparedStatement prst2 = null;

        ResultSet rs = null;

        System.out.println("获取到的sqlite连接对象：" + connection);

        try {
            DatabaseMetaData md = connection.getMetaData();  // 获取数据库的数据源
            System.out.println("该sqlite数据库是否支持多条SQL语句同时执行：" + md.supportsBatchUpdates());
            prst1 = connection.prepareStatement("SELECT ID, NAME, GENDER, BIRTH_DATE FROM STUDENT");
            prst2 = connection.prepareStatement("SELECT ID, NAME, GENDER, BIRTH_DATE FROM STUDENT");

            rs = prst1.executeQuery();
            while(rs.next()) {
                System.out.print("id:" + rs.getString("id")
                    + ", name:" + rs.getString("name")
                    + ", gender:" + rs.getString("gender")
                    + ", birth_date:" + rs.getString("birth_date") + "\r\n");
            }
            rs = prst2.executeQuery();
            while(rs.next()) {
                System.out.print("id:" + rs.getString("id")
                        + ", name:" + rs.getString("name")
                        + ", gender:" + rs.getString("gender")
                        + ", birth_date:" + rs.getString("birth_date") + "\r\n");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(new AutoCloseable[]{connection, prst1, prst2, rs});
        }

        System.out.println("sqlite连接对象已关闭。");

    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:C:/development/storage/comma.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
