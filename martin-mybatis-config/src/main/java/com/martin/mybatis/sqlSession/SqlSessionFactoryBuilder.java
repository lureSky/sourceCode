package com.martin.mybatis.sqlSession;

import com.martin.mybatis.config.Configuration;
import com.martin.mybatis.config.XMLConfigParser;
import com.martin.mybatis.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 * 使用构建者模式对SqlSessionFactory进行创建
 *
 */
public class SqlSessionFactoryBuilder {

	public SqlSessionFactory build(InputStream inputStream) {
		// 获取Configuration对象
		Document document = DocumentUtils.readDocument(inputStream);
		XMLConfigParser configParser = new XMLConfigParser();
		Configuration configuration = configParser.parser(document.getRootElement());
		return build(configuration);
	}

	public SqlSessionFactory build(Reader reader) {
		return null;
	}

	private SqlSessionFactory build(Configuration configuration) {
		return new DefaultSqlSessionFactory(configuration);
	}
}
