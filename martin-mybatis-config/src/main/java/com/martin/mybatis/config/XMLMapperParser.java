package com.martin.mybatis.config;

import org.dom4j.Element;

import java.util.List;

public class XMLMapperParser {

    private Configuration configuration;

    public XMLMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     *<mapper namespace="test">
     * 	<select id="findUserById"
     * 		parameterType="com.martin.mybatis.po.User"
     * 		resultType="com.martin.mybatis.po.User"
     * 		statementType="prepared">
     * 		SELECT * FROM user WHERE id = #{id} AND username like '%${username}'
     * 		<if test="username != null and username !='' ">
     * 			AND username like '%${username}'
     * 			<if test="username != null and username !=''">
     * 				AND 1=1
     * 			</if>
     * 		</if>
     * 	</select>
     * </mapper>
    */
    public void parse(Element rootElement) {
        //mapper下面会有sql片段标签，resultMap标签，直接解析处理      statement相关标签需要用Statement解析器
        String namespace = rootElement.attributeValue("namespace");
        //TODO Xpath改进 select|update|delete|insert
        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements) {
            //每一种（select update insert delete）  都对应一个statement
            XMLStatementParser statementParser = new XMLStatementParser(configuration);
            statementParser.parseStatement(selectElement,namespace);
        }

    }
}
