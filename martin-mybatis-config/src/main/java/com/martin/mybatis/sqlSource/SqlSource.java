package com.martin.mybatis.sqlSource;

import com.martin.mybatis.config.BoundSql;

/**
 * 描述：
 * 该接口的主要作用是
 *可以获取被JDBC程序直接执行的Sql语句
 *  怎么获取按照实现类操作
*/
public interface SqlSource {

    /**
     * 功能描述: BoundSql 是解析成Jdbc可以运行的Sql语句，因此需要有几个参数
     *  1.Sql
     *  2.参数解析集合
     *
     *  类似于 select * from user where id = #{id} and sex = #{sex} and username = '${username}'
     *  username 可以直接解析，因为是${}
     *  如果是#{} 需要使用？代替，占位符思想，因此需要有一个参数解析集合，保存位置的相关信息
     * @return: BoundSql
     * @mail: 867403822@qq.com       
     * @date: 2020-12-09 16:11
    */
    BoundSql getBoundSql(Object param);
}
