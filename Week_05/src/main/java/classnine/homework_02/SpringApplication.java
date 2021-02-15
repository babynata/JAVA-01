package classnine.homework_02;

import classnine.homework_02.entity.Classroom;
import classnine.homework_02.entity.Student;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Collections;

public class SpringApplication {

    public static void main(String[] args){
        //1.xml注入
        System.out.println("--->config by xml...");
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("homework_02.xml");

        Student student_01 = (Student) xmlContext.getBean("student_01");
        student_01.info();

        Student student_02 = (Student) xmlContext.getBean("student_02");
        student_02.info();

        Classroom classA = (Classroom) xmlContext.getBean("class_A");
        classA.info();

        //2.annotation注入
        System.out.println("--->config by annotation...");
        ApplicationContext annotationContext = new AnnotationConfigApplicationContext("classnine.homework_02.config", "classnine.homework_02.entity");
        Student student_03 = (Student) annotationContext.getBean("student_03");
        student_03.info();

        Student student_04 = (Student) annotationContext.getBean("student_04");
        student_04.info();

        Classroom classB = (Classroom) annotationContext.getBean("class_B");
        classB.info();

        //3.beanDefinition注入
        System.out.println("--->config by beanDefinition...");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        ConstructorArgumentValues cargs = new ConstructorArgumentValues();
        cargs.addIndexedArgumentValue(0, 5);
        cargs.addIndexedArgumentValue(1, "Nata");
        cargs.addIndexedArgumentValue(2, "C");
        AbstractBeanDefinition student_05 = new RootBeanDefinition(Student.class, cargs, null);
        beanFactory.registerBeanDefinition("student_05", student_05);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name","C");
        propertyValues.addPropertyValue("students", Collections.singletonList(new Student(5,"Nata","C")));
        AbstractBeanDefinition classroom_c = new RootBeanDefinition(Classroom.class, null, propertyValues);
        beanFactory.registerBeanDefinition("classroom_c", classroom_c);

        //get bean
        Student student05 = (Student) beanFactory.getBean("student_05");
        student05.info();
        Classroom classroomC = (Classroom) beanFactory.getBean("classroom_c");
        classroomC.info();
    }

}
