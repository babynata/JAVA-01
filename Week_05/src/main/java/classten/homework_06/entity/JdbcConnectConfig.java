package classten.homework_06.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JdbcConnectConfig {

    private String driverName;

    private String url;

    private String usr;

    private String pwd;
}
