package build.director;

import build.builder.StudentBuilder;
import build.product.Student;

// 导演类/测试类
public class BuildDemo {

	public static void main(String[] args) {
		
		StudentBuilder builder = new StudentBuilder();
		// 决定如何创建一个Student
		//是够构建者模式主要是可以1.不需要太清楚所有的参数细节，2.可以直接省去很多空字段   3.构建更安全
		Student student = builder.age(1).name("zhangsan").father("zhaosi").build();
		
		//builder.build(xxxxxxxx).build();
		System.out.println(student);

	}
}
