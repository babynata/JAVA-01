package classnine.homework_02.entity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("student_04")
public class Student_04 extends Student{

    @PostConstruct
    public void init() {
        setId(4);
        setName("Justin");
        setClassNo("B");
    }
}
