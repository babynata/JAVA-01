package classnine.homework_03.manager;


import classnine.homework_03.entity.Klass;
import classnine.homework_03.entity.Student;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.CollectionUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class KlassDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return Klass.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        List<Element> childs = parseChildElement(element,"ref");
        for (Element child : childs) {
            String beanName = child.getAttribute("bean");
            builder.addPropertyReference("student", beanName);
        }

    }


    private List<Element> parseChildElement(Element element, String childEleName) {
        List<Element> childs = DomUtils.getChildElementsByTagName(element, childEleName);
        if (CollectionUtils.isEmpty(childs)) {
            List<Element> subChilds = DomUtils.getChildElements(element);
            for (Element element1 : subChilds) {
                childs = parseChildElement(element1, childEleName);
            }
        }
        return childs;
    }
}
