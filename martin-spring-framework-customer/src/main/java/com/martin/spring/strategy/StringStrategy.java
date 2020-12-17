package com.martin.spring.strategy;

/**
 * @author caofeng
 * @date 2020/12/17 16:59
 */
public class StringStrategy implements BasicStrategy{
    @Override
    public Object parseToType( String value) {
        return value;
    }

    @Override
    public boolean isOk(Class<?> targetType) {
        return targetType == String.class;
    }
}
