package com.martin.spring.definition;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/15 16:56
 */
@Data
@NoArgsConstructor
public class BeanDefinition {
    private String clazzName;
    private String beanName;
    private String initMethod;
    private String scope;

    /**
     * bean中的属性信息
     */
    private List<PropertyValue> propertyValues = new ArrayList<>();

    public BeanDefinition(String initMethod, String scope) {
        this.initMethod = initMethod;
        this.scope = scope;
    }

    public BeanDefinition(String beanName, String simpleName, String initMethod, String scope) {
        this.beanName = beanName;
        this.clazzName = simpleName;
        this.initMethod = initMethod;
        this.scope = scope;
    }

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValues.add(pv);
    }

    public boolean isSingleton() {
        return "singleton".equals(this.scope) || "".equals(this.scope);
    }

    public boolean isPrototype() {
        return "prototype".equals(this.scope);
    }

}
