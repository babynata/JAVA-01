package classnine.homework_03.manager;

import classnine.homework_03.entity.Student;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class StudentDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        builder.getBeanDefinition().getPropertyValues().addPropertyValue("id",Integer.valueOf(element.getAttribute("idNo")));
        builder.getBeanDefinition().getPropertyValues().addPropertyValue("name",element.getAttribute("name"));
    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Student.class;
    }
}
