package pers.conan.easystorage.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.Sql;

public class TestSelect {
    
    private static final Logger LOG = Logger.getLogger(TestSelect.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        
        Connection connection = createConnection();
        
        LOG.debug("获取到的数据库连接是否为空：" + (connection == null)); 
        
        ClientCommand command = ClientCommand.build(connection);
        
        try {
            Stream<Student> students = (Stream<Student>) command.select(
                    "STUDENT", "id <> ?", null, new Object[] {1}, Student.class)
                                        .execute()
                                        .toStream();
            long count = students
                            .filter(item -> item.getId() == 1)
                            .count();
            LOG.debug("ID = 1的记录数有" + count + "个");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        Sql.close(new AutoCloseable[]{connection});

    }
    
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection createConnection() {
        
        Connection connection = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:c:/development/storage/comma.db");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return connection;
    }

}
