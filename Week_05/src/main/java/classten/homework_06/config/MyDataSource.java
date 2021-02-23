package classten.homework_06.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;

import javax.sql.DataSource;

@Data
public class MyDataSource {

    private DataSource dataSource;

    public MyDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("org.h2.Driver");
        config.setUsername("test");
        config.setPassword("test1234");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        dataSource = new HikariDataSource(config);
    }
}
