package com.martin.spring.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectUtils {

	/**
	 * 使用构造器创建实例
	 * @param clazz
	 * @param args
	 * @return
	 */
	public static Object createObject(Class<?> clazz, Object... args) {
		try {
			Constructor<?> constructor = clazz.getConstructor();
			// 默认调用无参构造进行对象的创建
			return constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setProperty(Object beanInstance, String name, Object valueToUse) {
		try {
			Class<?> clazz = beanInstance.getClass();
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			field.set(beanInstance, valueToUse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Class<?> getTypeByFieldName(String beanClassName, String name) {
		try {
			Class<?> clazz = Class.forName(beanClassName);
			Field field = clazz.getDeclaredField(name);
			return field.getType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void invokeMethod(Object beanInstance, String initMethod) {
		try {
			Class<?> clazz = beanInstance.getClass();
			Method method = clazz.getDeclaredMethod(initMethod);
			// 设置允许访问私有方法和变量，此处也称之为暴力破解
			method.setAccessible(true);
			method.invoke(beanInstance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
