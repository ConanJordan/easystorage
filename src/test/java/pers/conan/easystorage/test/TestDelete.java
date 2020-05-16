package pers.conan.easystorage.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.Sql;

public class TestDelete {
    
    private static final Logger LOG = Logger.getLogger(TestDelete.class);

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
            
            resultCount = command.delete("STUDENT", "ID = ?", new Object[] {1}, null)
                    .execute()
                    .toResultCount();
            
            LOG.debug("成功删除" + resultCount + "条数据。");
            
            connection.commit(); // 提交
            
        } catch (Exception e) {
            try {
                connection.rollback();
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
