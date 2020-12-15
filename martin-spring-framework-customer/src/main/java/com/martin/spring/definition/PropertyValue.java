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

    private Object value;
}
