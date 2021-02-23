package classten.homework_06.service.impl;

import classten.homework_06.entity.JdbcConnectConfig;
import classten.homework_06.service.JdbcConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyJdbcConnectionFactory implements JdbcConnectionFactory {

    private JdbcConnectConfig jdbcConnectConfig;

    private final Queue<Connection> connections = new ConcurrentLinkedQueue<>();

    public MyJdbcConnectionFactory(JdbcConnectConfig jdbcConnectConfig) {
        this.jdbcConnectConfig = jdbcConnectConfig;
        init();
    }

    private void init() {
        try {
            Class.forName(this.jdbcConnectConfig.getDriverName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connections.size() < 20) {
            connections.add(createConnection());
        }
        return connections.poll();
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(this.jdbcConnectConfig.getUrl(), this.jdbcConnectConfig.getUsr(), this.jdbcConnectConfig.getPwd());
    }
}
