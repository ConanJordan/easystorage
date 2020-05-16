package pers.conan.easystorage.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.Sql;

public class TestDelete {
    
    private static final Logger LOG = Logger.getLogger(TestDelete.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        
        Connection connection = TestSelect.createConnection();
        
        LOG.debug("获取到的数据库连接是否为空：" + (connection == null));
        
        try {
            connection.setAutoCommit(false);
            
            ClientCommand command = ClientCommand.build(connection);
            
            int resultCount = 0;
            /*
             * resultCount = command.delete("DELETE FROM STUDENT WHERE ID = ?", new Object[]
             * {2}) .execute() .toResultCount();
             */
            
            /*
             * resultCount = command.delete("STUDENT", "ID = ?", new Object[] {1})
             * .execute() .toResultCount();
             */
            
            /*
             * Student student = new Student(); student.setId(3);
             * 
             * resultCount = command.delete("STUDENT", student, Student.class) .execute()
             * .toResultCount();
             */
            
            List<Student> students = (List<Student>) command.select("STUDENT", Student.class)
                                            .execute()
                                            .toList();
            
            //students.stream().forEach(element -> LOG.debug(element.toString()));
            
            resultCount = command.delete("STUDENT", students, Student.class)
                                 .execute()
                                 .toResultCount();

            LOG.debug("成功删除" + resultCount + "条数据。");
            
            connection.commit(); // 提交
            LOG.debug("执行成功，已提交。");
            
        } catch (Exception e) {
            try {
                connection.rollback();
                LOG.debug("执行失败，已回滚。");
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } // 回滚
            e.printStackTrace();
        } finally {
            Sql.close(new AutoCloseable[] {connection});
        }

    }

}
