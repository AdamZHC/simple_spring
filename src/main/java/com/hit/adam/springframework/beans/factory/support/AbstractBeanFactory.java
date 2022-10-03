package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.BeanFactory;
import com.hit.adam.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) throws BeansException {
        /**
         * 这里是一样的，因为就是在这里的情况就是为了让单例注册表继承进来然后就是
         * 可以使得模块融合起来，从而实现对应的功能和合并
         */
        Object bean = getSingleton(beanName);
        if(bean != null)
            return bean;
        /**
         * 注意这里应当有的逻辑就是这个BeanDefinition已经创建过了
         * 所以说这里需要的时候才去创建加载，本质上就是一种懒加载
         */
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }
    /**
     * 最基本的情况就是我要使得这个definition的注册表合理起来
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建bean的方法本质上通过Bean的定义类来实现对应的功能
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
