package classten.homework_06.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface JdbcConnectionFactory {

    Connection getConnection() throws SQLException;
}
