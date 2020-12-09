package com.martin.mybatis.config;

import com.martin.mybatis.utils.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 解析document变为Configuration
 *
 * 解析全局配置文件
 * @author: Caofeng
 * @mail: 867403822@qq.com
 * @date: 2020-12-08 18:54
*/
public class XMLConfigParser {

    private Configuration configuration;

    public XMLConfigParser() {
        configuration = new Configuration();
    }

    //解析对象
    public Configuration parser(Element rootElement) {
        //对照标签进行层级解析
        /**
         * rootElement->configuration
         * 下面有environments
         * mappers
        */
        parseEnvironment(rootElement.element("environments"));
        //重点，映射文件的解析关键
        parseMappers(rootElement.element("mappers"));
        return configuration;
    }

    /**
     * @Description: 解析mappers子标签，该便签会解析所有的映射文件
     * @param element
     * @return: void
     * @author: Caofeng
     * @mail: 867403822@qq.com       
     * @date: 2020-12-08 21:42
    */
    private void parseMappers(Element element) {
        List<Element> mappers = element.elements("mapper");
        for (Element mapper : mappers) {
            parseMapper(mapper);
        }
    }

    private void parseMapper(Element mapper) {
        //映射相关操作
        //1.获取映射文件路径
        String resource = mapper.attributeValue("resource");
        //2.获取指定路径的IO流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //3.获取document对象
        Document document = DocumentUtils.readDocument(inputStream);
        //4.按照Mapper标签语义   解析生成的configuration  需要将mapper也放入到configuration
        XMLMapperParser mapperParser = new XMLMapperParser(configuration);

        mapperParser.parse(document.getRootElement());
    }

    private void parseEnvironment(Element environments) {
        String defaultEnvId = environments.attributeValue("default");
        if (defaultEnvId == null && "".equals(defaultEnvId)) {
            //如果为空直接退出即可
            return;
        }
        //获取environment
        List<Element> envs = environments.elements("environment");
        //拿出environment
        for (Element env : envs) {
            //挨个解析
            String envId = env.attributeValue("id");
            //判断默认ID和envId是否一致，一致才能继续解析
            if (defaultEnvId.equals(envId)) {
                parserEnvironment(env);
            }
        }
    }

    /**
     * <dataSource type="DBCP">
     * 	    <property name="driver" value="com.mysql.jdbc.Driver"></property>
     * 		<property name="url"
     * 			value="jdbc:mysql://localhost:3306/ssm"></property>
     * 		<property name="username" value="root"></property>
     * 		<property name="password" value="root"></property>
     * </dataSource>
    */
    private void parserEnvironment(Element env) {
        Element dataSourceEnv = env.element("dataSource");
        String type = dataSourceEnv.attributeValue("type");
        type = type == null || type.equals("") ? "DBCP" :type;
        //拿到所有的element，然后进行解析操作
        if (type.equals("DBCP")) {
            BasicDataSource dataSource = new BasicDataSource();
            Properties properties = parseProperty(dataSourceEnv);
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            //解析好了配置文件，放在configuration中
            configuration.setDataSource(dataSource);
        }
    }

    /**
     * 解析properties
    */
    private Properties parseProperty(Element dataSourceEnv) {
        Properties properties = new Properties();
        List<Element> elements = dataSourceEnv.elements("property");
        for (Element element : elements) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        return properties;
    }

}
