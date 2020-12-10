package com.martin.mybatis.sqlSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/9 16:08
 */
public class BoundSql {

    private String sql;

    //参数名称，参数类型，参照JDBC中PreStatement执行流程，       需要占位符对应的下标，我们可以用名称代替，要有参数类型，进行解析
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


    public BoundSql(String sql,List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }
}
