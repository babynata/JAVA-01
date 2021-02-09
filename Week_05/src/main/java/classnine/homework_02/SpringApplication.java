package classnine.homework_02;

import classnine.homework_02.entity.Classroom;
import classnine.homework_02.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {

    public static void main(String[] args){
        System.out.println("--->config by xml...");
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("homework_02.xml");

        Student student_01 = (Student) xmlContext.getBean("student_01");
        student_01.info();

        Student student_02 = (Student) xmlContext.getBean("student_02");
        student_02.info();

        Classroom classA = (Classroom) xmlContext.getBean("class_A");
        classA.info();


        System.out.println("--->config by annotation...");
        ApplicationContext annotationContext = new AnnotationConfigApplicationContext("classnine.homework_02.config", "classnine.homework_02.entity");
        Student student_03 = (Student) annotationContext.getBean("student_03");
        student_03.info();

        Student student_04 = (Student) annotationContext.getBean("student_04");
        student_04.info();

        Classroom classB = (Classroom) annotationContext.getBean("class_B");
        classB.info();
    }

}
