package singleton;

/**
 * 
 * @author 怡吾宇
 *
 *双重检查加锁
 */
public class Student6 {

    //双重检测
	private volatile static Student6 student;
	private Student6() {}
    public static Student6 getInstance(){
        if(student == null){
        // B线程检测到student不为空
            synchronized(Student6.class){
                if(student == null){
                    student = new Student6();
                    // A线程被指令重排了，刚好先赋值了；但还没执行完构造函数。
                }
            }
        }
		return student;// 后面B线程执行时将引发：对象尚未初始化错误。
    }

}
