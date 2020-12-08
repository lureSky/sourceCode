package com.martin.mybatis.utils;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class DocumentUtils {

    //根据刘创建对象
    public static Document readDocument(InputStream inputStream) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            return document;
        }catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
