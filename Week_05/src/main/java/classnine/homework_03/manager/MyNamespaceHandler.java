package classnine.homework_03.manager;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("student",new StudentDefinitionParser());
        registerBeanDefinitionParser("klass",new KlassDefinitionParser());
    }
}
