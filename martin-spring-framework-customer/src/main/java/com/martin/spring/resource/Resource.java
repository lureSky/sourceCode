package com.martin.spring.resource;

import java.io.InputStream;

/**
 * 提供资源的输入流操作
 * @author caofeng
 * @date 2020/12/15 15:28
 */
public interface Resource {
    InputStream getResourceAsStream();
}
