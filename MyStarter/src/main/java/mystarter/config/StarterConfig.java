package mystarter.config;

import mystarter.entity.Klass;
import mystarter.entity.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StarterConfig {

    @Bean(name = "student100")
    @Order(1)
    public Student createStudent() {
        Student student = new Student();
        student.setId(100);
        student.setName("Jane");
        return student;
    }

    @Bean(name = "class1")
    @ConditionalOnBean(name = "student100")
    public Klass createKlass() {
        List<Student> students = new ArrayList<>();
        students.add(createStudent());
        Klass klass = new Klass();
        klass.setStudents(students);
        return klass;
    }
}
