package com.hit.adam.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.PropertyValue;
import com.hit.adam.springframework.beans.PropertyValues;
import com.hit.adam.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.hit.adam.springframework.beans.factory.config.BeanDefinition;
import com.hit.adam.springframework.beans.factory.config.BeanPostProcessor;
import com.hit.adam.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 注意到这里可以选择性地去继承
 */
@SuppressWarnings("All")
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    /**
     * 这里确实也是实现的核心
     * 考虑如何去实现对应的方法把这里的空参构造转化一波
     */
    public Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            //这里就是另外一个核心逻辑的实现
            //因为是懒加载，需要的时候才会需要
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            //核心逻辑也在这里就是在createBean的时候，执行这个方法initializeBean
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //最后才放到这个单例表中
        addSingletonObject(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            /**
             * 这里就只找一个构造方法
             * 不能只看长度吧
             */
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        /**
         * 注册的时候就设置PropertyValues
         * 最终实例化的时候需要使用这个实例化
         */
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for(PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            /**
             * 表示说如果说这里是一个引用类型话，那就获取对应的Bean的类型，然后再去实例化，所以说这里
             * 本质上是一个递归函数
             */
            if(value instanceof BeanReference) {
                value = getBean(((BeanReference) value).getBeanName());
            }
            BeanUtil.setFieldValue(bean, name, value);
        }
    }
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        //这里是对所有的进行操作
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }
    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
