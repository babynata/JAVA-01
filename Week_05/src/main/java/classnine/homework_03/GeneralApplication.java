package classnine.homework_03;

import classnine.homework_03.entity.Student;
import classnine.homework_03.manager.BeanFactory;
import classnine.homework_03.manager.impl.XmlBeanFactory;

public class GeneralApplication {

    public static void main(String[] args){
        BeanFactory xmlBeanFactory = new XmlBeanFactory("/Users/natalie/IdeaProject/JAVA-01/Week_05/src/main/resources/custom/xml-beans-config.xml");
        Student student123 = (Student) xmlBeanFactory.getBean("student123");
        student123.init();
        System.out.println(student123.toString());
    }
}
