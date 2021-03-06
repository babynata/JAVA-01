package classnine.mybeanfactory.manager;

import classnine.mybeanfactory.config.PropertyValue;
import classnine.mybeanfactory.manager.impl.GeneralBeanDefinition;
import net.sf.cglib.beans.BeanGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BaseBeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String, Object> beanMap = new HashMap<>();

    @Override
    public void registerBean(String name, BaseBeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    @Override
    public Object getBean(String beanName) {
        if (beanMap.get(beanName) == null) {
            if (beanDefinitionMap.get(beanName) == null) {
                throw new RuntimeException("no bean definition was found");
            }
            GeneralBeanDefinition beanDefinition = (GeneralBeanDefinition) beanDefinitionMap.get(beanName);
            Class classType = beanDefinition.getClassType();
            PropertyValue propertyValue = beanDefinition.getPropertyValue();
            Object proxyObj = getProxyObject(classType, propertyValue);
            beanMap.put(beanName, proxyObj);
        }
        return beanMap.get(beanName);
    }

    private Object getProxyObject(Class classType, PropertyValue propertyValue) {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.setSuperclass(classType);
        Object object = beanGenerator.create();

        Map<String, String> propertyValues = propertyValue.getPropertyValues();
        for (Map.Entry<String, String> entry : propertyValues.entrySet()) {
            String fieldName = entry.getKey();
            String mehtodName = "set" + StringUtils.capitalize(fieldName);
            try {
                Field field = classType.getDeclaredField(fieldName);
                Class fieldType = field.getType();
                ReflectionUtils.invokeMethod(classType.getMethod(mehtodName, fieldType), object, castValue(entry.getValue(),fieldType));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    private Object castValue(String object, Class fieldType) {
        if (fieldType == int.class) {
            return new Integer(object);
        }
        return object;
    }

}
