package com.martin.mybatis.config;

import java.io.InputStream;
import java.io.Reader;

public class Resources {

    //获取输入流，xml文件
    public static InputStream getResourceAsStream(String resource) {
        return Resources.class.getClassLoader().getResourceAsStream(resource);
    }

    //也可以返回Reader，主要是封装细节
    public static Reader getResourceAsReader(String resource) {
        return null;
    }
}
