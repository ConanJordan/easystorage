package pers.conan.easystorage.test;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.Sql;

public class TestInsert {
    
    private static final Logger LOG = Logger.getLogger(TestInsert.class);

    public static void main(String[] args) {
        Connection connection = DataBaseFactory.createConnection();
        
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        LOG.debug("获取到的数据库连接是否为空：" + (connection == null));
        
        ClientCommand command = ClientCommand.build(connection);
        
        try {
            /*
             * command.insert("INSERT INTO PLAYERS (" +
             * "ID, FIRST_NAME, LAST_NAME, UNIFORM_NUMBER, TEAM_ID) " + "VALUES (" +
             * "SEQ_PLAYER_ID.NEXTVAL, ?, ?, ?, ?)", new Object[] {"Lebron", "James", 23,
             * 4});
             */
            
            /*
             * List<Player> players = new ArrayList<>();
             * 
             * players.add(new Player("Steven", "Curry", 30, 1)); players.add(new
             * Player("Kevin", "Durant", 35, 1));
             * 
             * command.insert("PLAYERS", players, Player.class);
             */
            
            Player jordan = new Player("Michle", "Jordan", 23, 0);
            
            int resultCount = command.insert("PLAYERS", jordan, jordan.getClass())
                                     .execute()
                                     .toResultCount();
            
            LOG.debug("成功插入" + resultCount + "条数据。");
            
            connection.commit();
            LOG.debug("插入成功，提交更新。");
            
        } catch (Exception e) {
            LOG.debug("更新失败，数据回滚。");
            LOG.error(e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } // 回滚
        } finally {
            Sql.close(new AutoCloseable[] {connection}); // 释放数据库资源
        }
    }

}
