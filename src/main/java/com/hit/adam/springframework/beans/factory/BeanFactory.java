package com.hit.adam.springframework.beans.factory;

import com.hit.adam.springframework.beans.BeansException;

/**
 * 最最核心的接口
 * 以及最最的核心的方法就是这个 getBean
 */
public interface BeanFactory {
    /**
     * 无参构造方法
     * @param name: 获取对应的对象，需要从单例注册表中获取
     * @return 对应的对象
     * @throws BeansException 对应的Beans异常
     */
    Object getBean(String name) throws BeansException;

    /**
     * 有参构造方法
     * @param name: 获取对应的对象，需要从单例注册表中获取
     * @param args: 对应的参数
     * @return 对应的对象
     */
    Object getBean(String name, Object... args) throws BeansException;
}
