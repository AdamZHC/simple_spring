package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
@SuppressWarnings("All")
/**
 * 相当于是用到了java的技术点
 * 也就是说用某个类的constructor来实现对应的实例化
 * 用类实现实例化，但是需要这个类的某一个真正的constructor来实现
 * 并且需要把参数传入
 *
 * 所以说这里的BeanDefinition是非常关键的
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if(ctor != null) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Fail to instantiate");
        }
    }
}
