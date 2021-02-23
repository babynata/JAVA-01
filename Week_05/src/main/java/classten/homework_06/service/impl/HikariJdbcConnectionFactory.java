package classten.homework_06.service.impl;

import classten.homework_06.config.MyDataSource;
import classten.homework_06.service.JdbcConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariJdbcConnectionFactory implements JdbcConnectionFactory {

    private MyDataSource myDataSource;

    public HikariJdbcConnectionFactory(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return myDataSource.getDataSource().getConnection();
    }
}
