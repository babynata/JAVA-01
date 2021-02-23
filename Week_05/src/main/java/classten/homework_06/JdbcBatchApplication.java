package classten.homework_06;

import classten.homework_06.entity.JdbcConnectConfig;
import classten.homework_06.config.MyDataSource;
import classten.homework_06.service.impl.BaseJdbcBatchService;
import classten.homework_06.service.impl.HikariJdbcConnectionFactory;
import classten.homework_06.service.impl.JdbcBatchServiceImpl;
import classten.homework_06.service.impl.MyJdbcConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcBatchApplication {

    public static void main(String[] args){
        JdbcConnectConfig jdbcConnectConfig = new JdbcConnectConfig("org.h2.Driver", "jdbc:h2:~/test", "test", "test1234");
        BaseJdbcBatchService myBatchService = new JdbcBatchServiceImpl(new MyJdbcConnectionFactory(jdbcConnectConfig));

        //hikari 线程池
        BaseJdbcBatchService batchService = new JdbcBatchServiceImpl(new HikariJdbcConnectionFactory(new MyDataSource()));

        List<List<Object>> list = new ArrayList<>();
        List<Object> deleteParams_01 = new ArrayList<>();
        deleteParams_01.add(2);

        List<Object> deleteParams_02 = new ArrayList<>();
        deleteParams_02.add(3);

        list.add(deleteParams_01);
        list.add(deleteParams_02);

        String deleteSql = "delete from t_user where id=?";
        try {
            int deleteCount = batchService.batchExecute(deleteSql, list);
            System.out.println("deleteCount: "+deleteCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list.clear();
        List<Object> insertParams_01 = new ArrayList<>();
        insertParams_01.add(2);
        insertParams_01.add("Nata");
        insertParams_01.add("12345");
        insertParams_01.add(25);
        insertParams_01.add("1995-07-30 15:00:00");
        list.add(insertParams_01);

        List<Object> insertParams_02 = new ArrayList<>();
        insertParams_02.add(3);
        insertParams_02.add("Nata");
        insertParams_02.add("12345");
        insertParams_02.add(25);
        insertParams_02.add("1995-07-30 15:00:00");
        list.add(insertParams_02);

        String insertSql = "INSERT INTO t_user VALUES (?,?, ?, ?, ?)";
        try {
            int insertCount = batchService.batchExecute(insertSql, list);
            System.out.println("insertCount: "+insertCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String selectSql = "SELECT id, name, password, age, birthday FROM t_user WHERE id=?";
        try {
            PreparedStatement preparedStatement = batchService.getConnection().prepareStatement(selectSql);
            preparedStatement.setLong(1, 2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id")+" | "+
                        resultSet.getString("name")+" | "+
                        resultSet.getString("password")+" | "+
                        resultSet.getShort("age")+" | "+
                        resultSet.getTimestamp("birthday"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
