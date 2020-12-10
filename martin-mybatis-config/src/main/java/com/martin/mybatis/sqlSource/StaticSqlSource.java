package com.martin.mybatis.sqlSource;

import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/10 20:00
 */
public class StaticSqlSource implements SqlSource{

    private String sql;

    private List<ParameterMapping> parameterMappings;

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        super();
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return new BoundSql(sql, parameterMappings);
    }
}
