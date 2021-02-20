package classnine.mybeanfactory.manager;

public interface BeanFactory {

    void registerBean(String name, BaseBeanDefinition beanDefinition);

    Object getBean(String beanName);
}
