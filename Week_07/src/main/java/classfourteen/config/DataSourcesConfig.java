package classfourteen.config;

import classfourteen.entity.MultipleDataSourcesEnum;
import classfourteen.manager.MultipleDataSourcesRouting;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourcesConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public HikariDataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public HikariDataSource slave1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public DataSource multipleDataSourcesRouting(@Qualifier("masterDataSource") HikariDataSource masterDataSource, @Qualifier("slave1DataSource") HikariDataSource slave1DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(MultipleDataSourcesEnum.MASTER, masterDataSource);
        targetDataSources.put(MultipleDataSourcesEnum.SLAVE1, slave1DataSource);

        MultipleDataSourcesRouting dataSourcesRouting = new MultipleDataSourcesRouting();
        dataSourcesRouting.setTargetDataSources(targetDataSources);
        dataSourcesRouting.setDefaultTargetDataSource(masterDataSource);
        return dataSourcesRouting;
    }
}
