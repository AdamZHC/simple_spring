package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路就是逐步拆分
 * 首先考虑这个部分的单例实现功能
 *
 * 本质上就是通过这一部分单例模式的注册功能
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 这里就是为了给其它的类使用的
     * 本质上是这样的，就是其实这个注册表里面的所有的东西就是给
     * 最终的外部调用的，但是实际上如果直接调用的话，模块就是没有拆分开
     * 所以说这样的不是很好，因此就是需要把模块拆分掉
     * @param beanName: bean类的名称
     * @param singletonObject: 对应的单例的类
     */
    protected void addSingletonObject(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
