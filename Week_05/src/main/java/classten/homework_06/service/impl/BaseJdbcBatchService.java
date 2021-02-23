package classten.homework_06.service.impl;

import classten.homework_06.service.JdbcBatchService;
import classten.homework_06.service.JdbcConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseJdbcBatchService implements JdbcBatchService, JdbcConnectionFactory {

    JdbcConnectionFactory jdbcConnectionFactory;

    public BaseJdbcBatchService(JdbcConnectionFactory jdbcConnectionFactory) {
        this.jdbcConnectionFactory = jdbcConnectionFactory;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return jdbcConnectionFactory.getConnection();
    }
}
