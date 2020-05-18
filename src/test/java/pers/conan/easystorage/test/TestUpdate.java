package pers.conan.easystorage.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import pers.conan.easystorage.database.ClientCommand;
import pers.conan.easystorage.util.Sql;

public class TestUpdate {
    
    private static final Logger LOG = Logger.getLogger(TestUpdate.class);

    @SuppressWarnings("unchecked")
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
            List<Team> teams = (List<Team>) command.select("TEAMS",null, null, Team.class)
                                      .execute()
                                      .toList();
            
            /*
             * teams.stream() .forEach(team -> LOG.debug(team.toString()));
             */
            for (int i = 0; i < teams.size(); i ++) {
                teams.get(i).setName(teams.get(i).getName() + teams.get(i).getId());
                LOG.debug(teams.get(i).toString());
            }
            
            int resultCount = command.update("TEAMS", teams, Team.class)
                                     .execute()
                                     .toResultCount();
            
            LOG.debug("成功更新" + resultCount + "条数据。");
            
            connection.commit();
            LOG.debug("更新成功，提交更新。");
        } catch (Exception e) {
            try {
                LOG.debug("更新失败，数据回滚。");
                connection.rollback(); // 回滚
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        
        Sql.close(new AutoCloseable[] {connection});

    }

}
