package com.martin.spring.po;

import lombok.Data;

/**
 * @author caofeng
 * @date 2020/12/15 16:39
 */
@Data
public class Course {
    private String name;

    private Integer age;

    public void init(){
        System.out.println("触发了初始化方法");
    }
}
