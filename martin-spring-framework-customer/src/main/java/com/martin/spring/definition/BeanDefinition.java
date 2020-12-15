package com.martin.spring.definition;

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

    public BeanDefinition(String clazzName, String beanName) {
        this.clazzName = clazzName;
        this.beanName = beanName;
    }

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValues.add(pv);
    }

}
