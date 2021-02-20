package classnine.mybeanfactory.manager.impl;

import classnine.mybeanfactory.config.PropertyValue;
import classnine.mybeanfactory.manager.BaseBeanDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralBeanDefinition implements BaseBeanDefinition{

    private String beanName;

    private Class<?> classType;

    private PropertyValue propertyValue;

    public GeneralBeanDefinition(String beanName, Class<?> classType) {
        this.beanName = beanName;
        this.classType = classType;
    }
}
