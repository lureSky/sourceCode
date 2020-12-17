package com.martin.spring.strategy;

/**
 * @author caofeng
 * @date 2020/12/17 16:56
 */
public interface BasicStrategy {

    Object parseToType(String value);

    boolean isOk(Class<?> targetType);
}
