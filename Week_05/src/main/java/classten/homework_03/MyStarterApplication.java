package classten.homework_03;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyStarterApplication {

    public static void main(String[] args){
        ApplicationContext applicationContext = SpringApplication.run(MyStarterApplication.class);
        ISchool iSchool = (ISchool) applicationContext.getBean("school");
        iSchool.ding();
    }
}
