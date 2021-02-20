package classnine.mybeanfactory;

import classnine.mybeanfactory.entity.Student;
import classnine.mybeanfactory.manager.BeanFactory;
import classnine.mybeanfactory.manager.impl.XmlBeanFactory;

public class GeneralApplication {

    public static void main(String[] args){
        BeanFactory xmlBeanFactory = new XmlBeanFactory("/Users/natalie/IdeaProject/JAVA-01/Week_05/src/main/resources/custom/xml-beans-config.xml");
        Student student123 = (Student) xmlBeanFactory.getBean("student123");
        student123.init();
        System.out.println(student123.toString());
        Student student100 = (Student) xmlBeanFactory.getBean("student100");
        student100.init();
        System.out.println(student100.toString());
    }
}
