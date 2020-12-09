package com.martin.mybatis.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/9 16:08
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void addParameterMapping(ParameterMapping parameterMapping) {
        parameterMappings.add(parameterMapping);
    }


    public BoundSql(String sql) {
        this.sql = sql;
    }
}
