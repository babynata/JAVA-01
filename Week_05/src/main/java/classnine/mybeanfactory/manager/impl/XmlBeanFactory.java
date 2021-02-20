package classnine.mybeanfactory.manager.impl;

import classnine.mybeanfactory.config.PropertyValue;
import classnine.mybeanfactory.manager.AbstractBeanFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class XmlBeanFactory extends AbstractBeanFactory{

    private File file;

    public XmlBeanFactory(String fileName) {
        this.file = new File(fileName);
        parseXmlConfig();
    }

    private void parseXmlConfig() {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element beans = document.getRootElement();
            List<Element> beanList = beans.elements();
            for (Element bean : beanList) {
                //id,classType attribute
                String beanName = bean.attributeValue("id");
                Class classType = Class.forName(bean.attributeValue("class"));
                GeneralBeanDefinition newBeanDefinition = new GeneralBeanDefinition(beanName, classType);

                //property
                PropertyValue propertyValue = new PropertyValue();

                List<Element> propertyValues = bean.elements();
                for (Element values : propertyValues) {
                    String name = values.attributeValue("name");
                    String value = values.attributeValue("value");
                    propertyValue.addPropertyValue(name,value);
                }
                newBeanDefinition.setPropertyValue(propertyValue);

                this.registerBean(beanName, newBeanDefinition);
            }
        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
