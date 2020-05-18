package pers.conan.easystorage.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import pers.conan.easystorage.util.Sql;

public class DataBaseFactory {
    
    private static final Logger LOG = Logger.getLogger(DataBaseFactory.class);
    
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/orcl";
    private static final String USERNAME = "c##dell";
    private static final String PASSWORD = "dell";

    public static void main(String[] args) {
        
        Connection connection = createConnection();
        
        LOG.debug("获取到的数据库连接是否为空：" + (connection == null));
        
        Sql.close(new AutoCloseable[] {connection});

    }
    
    public static Connection createConnection() {
        Connection connection = null;
        
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return connection;
    }

}
