package com.martin.mybatis.test;

import com.martin.mybatis.config.Configuration;
import com.martin.mybatis.utils.Resources;
import com.martin.mybatis.config.XMLConfigParser;
import com.martin.mybatis.dao.UserDao;
import com.martin.mybatis.dao.UserDaoImpl;
import com.martin.mybatis.po.User;
import com.martin.mybatis.sqlSession.SqlSessionFactory;
import com.martin.mybatis.sqlSession.SqlSessionFactoryBuilder;
import com.martin.mybatis.utils.DocumentUtils;
import org.dom4j.Document;
import org.junit.Test;

import java.io.InputStream;

public class UserDaoTest {

    @Test
    public void testInitConfiguration() throws Exception{
        //指定配置文件路径
        String resource = "SqlMapConfig.xml";
        //获取输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //获取document对象
        Document document = DocumentUtils.readDocument(inputStream);
        //解析document对象为Configuration
        XMLConfigParser xmlConfigParser = new XMLConfigParser();

        //从根便签开始解析
        Configuration configuration = xmlConfigParser.parser(document.getRootElement());
        System.out.println(configuration);
    }

    /**
     * 使用手写mybatis框架去实现的
     */
    @Test
    public void testQueryUserById2() {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // SqlSessionFactory的创建可能有几种创建方式，但是我还是不想要知道SqlSessionFactory的构造细节
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User param = new User();
        param.setId(1);
        User user = userDao.queryUserById2(param);
        System.out.println(user);
    }
}
