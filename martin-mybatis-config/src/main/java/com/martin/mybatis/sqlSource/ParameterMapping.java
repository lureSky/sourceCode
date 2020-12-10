package com.martin.mybatis.sqlSource;

/**
 * 参数映射类
*/
public class ParameterMapping {

    private String name;
    private Class<?> type;

    public ParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
