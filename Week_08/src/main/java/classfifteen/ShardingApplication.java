package classfifteen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("classfifteen.mapper")
public class ShardingApplication {

    public static void main(String[] args){
        SpringApplication.run(ShardingApplication.class, args);
    }
}
