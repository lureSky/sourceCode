package com.martin.spring.po;

import lombok.Data;

/**
 * @author caofeng
 * @date 2020/12/15 16:39
 */
@Data
public class Student {
    private String name;

    //课程的引用
    private Course course;
}
