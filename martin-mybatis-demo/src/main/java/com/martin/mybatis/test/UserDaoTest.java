package com.martin.mybatis.test;

import com.martin.mybatis.config.Configuration;
import com.martin.mybatis.config.Resources;
import com.martin.mybatis.config.XMLConfigParser;
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
    }
}
