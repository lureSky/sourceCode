package com.martin.spring.resource;

import java.io.InputStream;

/**
 * 读取classpath下的配置文件
 * @author caofeng
 * @date 2020/12/15 15:28
 */
public class ClasspathResource implements Resource{

    //传入地址
    private String location;
    public ClasspathResource(String location){
        this.location = location;
    }

    @Override
    public InputStream getResourceAsStream() {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
