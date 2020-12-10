package com.martin.mybatis.executor;

import com.martin.mybatis.config.Configuration;
import com.martin.mybatis.config.MappedStatement;
import com.martin.mybatis.sqlSource.BoundSql;
import com.martin.mybatis.sqlSource.ParameterMapping;
import com.martin.mybatis.sqlSource.SqlSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/10 20:08
 */
public class SimpleExecutor extends BaseExecutor{


    @Override
    public List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param) {
        List<Object> results = new ArrayList<Object>();

        try {
            // 获取连接
            Connection connection = getConnection(configuration);
            // 获取sql语句
            BoundSql boundSql = getBoundSql(mappedStatement.getSqlSource(), param);

            String statementType = mappedStatement.getStatementType();

            // 使用mybatis的四大组件来优化
            if ("prepared".equals(statementType)) {
                // 创建Statement
                PreparedStatement statement = createStatement(connection, boundSql.getSql());
                // 设置参数
                handleParameter(statement, boundSql, param);
                // 执行Statement
                ResultSet resultSet = statement.executeQuery();
                // 处理结果
                handleResult(resultSet, mappedStatement, results);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    private void handleResult(ResultSet rs, MappedStatement mappedStatement, List<Object> results) throws Exception {
        // 从结果集中一行一行的取数据
        // 每一行数据，再一列一列的取数据（包括列的名称和列的值）
        // 最终将获取到的每一列的值都映射到目标对象的指定属性中（列的名称和属性名称要一致）
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        while (rs.next()) {
            // 要映射的结果目标对象
            Object result = resultTypeClass.newInstance();
            // 获取结果集的元数据（目的是取列的信息）
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Field field = resultTypeClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(result, rs.getObject(columnName));
            }

            results.add(result);
        }
    }

    private void handleParameter(PreparedStatement statement, BoundSql boundSql, Object param) throws Exception {
        // 判断入参的类型，如果是简单类型，直接处理
        if (param instanceof Integer) {
            statement.setObject(1, Integer.parseInt(param.toString()));
        } else if (param instanceof String) {
            statement.setObject(1, param.toString());
        } else {
            // 获取参数集合信息（#{}处理之后得到的参数信息）
            // 如果是POJO类型，则根据参数信息里面的参数名称，去入参对象中获取对应的参数值
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0; i < parameterMappings.size(); i++) {
                Object valueToUser = null;
                ParameterMapping parameterMapping = parameterMappings.get(i);
                // #{}中的参数名称，也应该和POJO类型中的属性名称一直
                String name = parameterMapping.getName();
                // 使用反射获取指定name的值
                Class<?> clazz = param.getClass();
                // 获取指定名称的属性对象
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                valueToUser = field.get(param);

                statement.setObject(i + 1, valueToUser);
            }
        }

    }

    private PreparedStatement createStatement(Connection connection, String sql) throws Exception {
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        return prepareStatement;
    }

    private BoundSql getBoundSql(SqlSource sqlSource, Object param) {
        BoundSql boundSql = sqlSource.getBoundSql(param);
        return boundSql;
    }

    private Connection getConnection(Configuration configuration) throws Exception {
        DataSource dataSource = configuration.getDataSource();
        Connection connection = dataSource.getConnection();
        return connection;
    }

}
