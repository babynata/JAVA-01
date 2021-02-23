package classten.homework_06;

import classten.homework_06.entity.JdbcConnectConfig;
import classten.homework_06.service.impl.MyJdbcConnectionFactory;

import java.sql.*;

public class JdbcApplication {

    public static void raw() {
        Connection connection;
        Statement statement;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test", "test", "test1234");
            //normal statement
            statement = connection.createStatement();
            int createRes = statement.executeUpdate("CREATE TABLE t_user (" +
                    "id BIGINT NOT NULL, " +
                    "name VARCHAR(20) NOT NULL," +
                    "password VARCHAR(32) NOT NULL, " +
                    "age SMALLINT, " +
                    "birthday TIMESTAMP," +
                    "PRIMARY KEY (id)" +
                    ");");
            System.out.println("create table: " + createRes);

            //insert
            statement = connection.createStatement();
            int insertRes = statement.executeUpdate("INSERT INTO t_user VALUES (1,'Nata', '12345', 28, '1989-09-15 15:00:00');");
            System.out.println("insert: " + insertRes);

            //prepare statement
            //select
            preparedStatement = connection.prepareStatement("SELECT id, name, password, age, birthday FROM t_user WHERE id=?");
            preparedStatement.setLong(1, 1);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id")+" | "+
                        resultSet.getString("name")+" | "+
                        resultSet.getString("password")+" | "+
                        resultSet.getShort("age")+" | "+
                        resultSet.getTimestamp("birthday"));
            }

            //update
            preparedStatement = connection.prepareStatement("UPDATE  t_user SET age=? where name=?");
            preparedStatement.setInt(1, 25);
            preparedStatement.setString(2,"Nata");
            int updateRes = preparedStatement.executeUpdate();
            System.out.println("update: " + updateRes);

            //delete
            preparedStatement = connection.prepareStatement("DELETE t_user WHERE id=?");
            preparedStatement.setLong(1, 1);
            int deleteRes = preparedStatement.executeUpdate();
            System.out.println("delete: "+deleteRes);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
//        raw();

        JdbcConnectConfig jdbcConnectConfig = new JdbcConnectConfig("org.h2.Driver", "jdbc:h2:~/test", "test", "test1234");
        MyJdbcConnectionFactory connectionFactory = new MyJdbcConnectionFactory(jdbcConnectConfig);
        try {
            String deleteSql = "delete from t_user";
            PreparedStatement preparedStatement = connectionFactory.getConnection().prepareStatement(deleteSql);
            int result = preparedStatement.executeUpdate();
            System.out.println("delete: " + result);

            String insertSql = "INSERT INTO t_user VALUES (?,?, ?, ?, ?)";
             preparedStatement = connectionFactory.getConnection().prepareStatement(insertSql);
            preparedStatement.setLong(1, 1);
            preparedStatement.setString(2, "Nata");
            preparedStatement.setString(3, "12345");
            preparedStatement.setInt(4, 25);
            preparedStatement.setString(5, "1995-07-30 15:00:00");
            result = preparedStatement.executeUpdate();
            System.out.println("insert: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
