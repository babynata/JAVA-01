package classnine.homework_02.config;

import classnine.homework_02.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean("student_03")
    public Student build_03() {
        return new Student(3, "Malone", "B");
    }

}
