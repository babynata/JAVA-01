package classnine.homework_03;

import classnine.homework_03.entity.Klass;
import classnine.homework_03.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyXmlApplication {

    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("homework_03.xml");
        Student student = (Student) applicationContext.getBean("student100");
        student.init();
        System.out.println(student.toString());

        Klass klass = (Klass) applicationContext.getBean("klassA");
        klass.dong();
    }
}
