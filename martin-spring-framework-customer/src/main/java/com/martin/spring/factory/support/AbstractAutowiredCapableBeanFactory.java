package com.martin.spring.factory.support;

import com.martin.spring.aware.Aware;
import com.martin.spring.aware.BeanFactoryAware;
import com.martin.spring.definition.BeanDefinition;
import com.martin.spring.definition.PropertyValue;
import com.martin.spring.definition.RuntimeBeanReference;
import com.martin.spring.definition.TypedStringValue;
import com.martin.spring.factory.AutowiredCapableBeanFactory;
import com.martin.spring.strategy.BasicStrategyContainer;
import com.martin.spring.utils.ReflectUtils;
import com.martin.spring.xml.InitializingBean;

import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/17 10:55
 */
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory implements AutowiredCapableBeanFactory {

    /**
     * 子类实现创建Bean
     *  DI的过程
     * @param name
     * @param bd
     * @return
     */
    @Override
    public Object CreateBean(String name, BeanDefinition bd){
        //1.创建beanDefinition的Bean的类型
        Class<?> type = getResolvedClass(bd);
        //2.构造器创建Bean的实例
        Object bean = createInstance(type);
        //3.填充Bean的属性
        pupulateBean(bean,bd);
        //4.调用Bean的初始化代码
        initialBean(bean,bd);
        return bean;
    }

    /**
     * bean的初始化过程
     * @param bean
     * @param bd
     */
    private void initialBean(Object bean, BeanDefinition bd) {
        //1.aware接口处理，标记接口   标记了aware，Spring帮忙注入可以使用@Autowired
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
        }
        //4.1  实现了InitializingBean接口的类  如果bean实现了initializingBean接口
        if (bean instanceof InitializingBean) {
            //后续添加
            ((InitializingBean) bean).afterPropertiesSet();
        }
        //4.2   在bean标签中的init-method
        initMehtod(bean,bd);

    }

    /**
     * 执行内置的init-Method指定方法
     * @param bean
     * @param bd
     */
    private void initMehtod(Object bean, BeanDefinition bd) {
        String initMethod = bd.getInitMethod();
        //TODO BeanPostProcessor执行前置方法
        if (initMethod != null && !"".equals(initMethod)) {
            ReflectUtils.invokeMethod(bean,initMethod);
        }
        //TODO BeanPostPocessor执行后置处理方法(AOP)
    }

    /**
     * 填充Bean的属性
     * @param bean
     * @param bd
     */
    private void pupulateBean(Object bean, BeanDefinition bd) {
        //取出bean定义的属性信息
        List<PropertyValue> propertyValues = bd.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            //找到了name和value，进行填充，实例按照name进行填充
            //value有TypeStringValue和Ref处理，普通的可以直接放入，如果是引用则要找到引用

            Object valueToUse = null;
            if (value instanceof TypedStringValue) {
                TypedStringValue typedStringValue = (TypedStringValue) value;
                //获取值
                String stringValue = typedStringValue.getValue();
                //获取类型
                Class<?> targetType = typedStringValue.getTargetType();
                BasicStrategyContainer basicStrategyContainer = new BasicStrategyContainer();
                valueToUse = basicStrategyContainer.parseByType(targetType,stringValue);
            }else if (value instanceof RuntimeBeanReference) {
                RuntimeBeanReference runtimeBeanReference = (RuntimeBeanReference) value;
                String ref = runtimeBeanReference.getRef();
                //容易引起循环依赖问题！！！！！
                valueToUse = getBean(ref);
            }
            //反射实现
            ReflectUtils.setProperty(bean,name,valueToUse);
        }
    }

    /**
     * bean的构建有多中
     *  1.通过构造器
     *  2.通过factoryBean（产生bean实例）--实例化工厂方式
     * @param beanClass
     * @return
     */
    private Object createInstance(Class<?> beanClass) {
        //TODO
        //如果存在实例工厂，则通过实例工厂创建实例
        //如果存在工厂方法，使用工厂方法创建实例
        //3.使用构造方法实现创建实例
        Object bean = ReflectUtils.createObject(beanClass);
        return bean;
    }

    private Class<?> getResolvedClass(BeanDefinition bd) {
        String clazzName = bd.getClazzName();
        //获取到class类型,使用反射，Class.forName
        Class<?> aClass = null;
        try {
            aClass = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aClass;
    }
}
