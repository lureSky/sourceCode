package com.martin.spring.definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 维护一个properties对象
 * @author caofeng
 * @date 2020/12/15 16:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyValue {
    private String name;

    /**
     * 主要存在两个对象：
     *  1.TypedStringValue              value属性，
     *  2.RuntimeBeanReference          ref属性，主要是bean内的ref引用对象相关
    */
    private Object value;
}
