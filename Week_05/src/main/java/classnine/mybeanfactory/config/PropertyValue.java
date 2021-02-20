package classnine.mybeanfactory.config;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PropertyValue {

    private final Map<String, String> propertyValues = new HashMap<>();

    public void addPropertyValue(String name, String value) {
        propertyValues.put(name, value);
    }
}
