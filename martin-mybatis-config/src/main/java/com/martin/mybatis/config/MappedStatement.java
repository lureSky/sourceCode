package com.martin.mybatis.config;

import com.martin.mybatis.sqlSource.SqlSource;

public class MappedStatement {

    private String statementId;
    private Class<?> parameterTypeClass;
    private Class<?> resultTypeClass;
    private String statementType;
    private SqlSource sqlSource;

    //使用构造者模式
    MappedStatement() {
    }

    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(String statementId,Class<?> parameterTypeClass,Class<?> resultTypeClass,String statementType,SqlSource sqlSource) {
            mappedStatement.statementId = statementId;
            mappedStatement.parameterTypeClass = parameterTypeClass;
            mappedStatement.resultTypeClass = resultTypeClass;
            mappedStatement.statementType = statementType;
            mappedStatement.sqlSource = sqlSource;
        }

        public MappedStatement build(){
            assert mappedStatement.statementId != null;
            assert mappedStatement.parameterTypeClass != null;
            assert mappedStatement.resultTypeClass != null;
            assert mappedStatement.statementType != null;
            assert mappedStatement.sqlSource != null;
            return mappedStatement;
        }
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public String getStatementType() {
        return statementType;
    }

    public Class<?> getResultTypeClass() {
        return resultTypeClass;
    }
}
