package com.hit.adam.springframework.beans.factory.config;

/**
 * 这里目前的思考就是通过单例的模式获取bean Object的过程
 * 这个method来抽象
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
