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
     *
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
        String namespace = rootElement.attributeValue("namespace");
        //此处可以Xpath语法，来进行通配
        List<Element> selectElements = rootElement.elements("select");
        XMLScriptParser scriptParser = new XMLScriptParser(configuration);

        for (Element selectElement : selectElements) {
            scriptParser.parseScript(selectElement);
        }

    }
}
