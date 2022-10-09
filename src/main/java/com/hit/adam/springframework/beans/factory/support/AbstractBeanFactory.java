package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.BeanFactory;
import com.hit.adam.springframework.beans.factory.config.BeanDefinition;
import com.hit.adam.springframework.beans.factory.config.BeanPostProcessor;
import com.hit.adam.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("All")
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        /**
         * 这里是一样的，因为就是在这里的情况就是为了让单例注册表继承进来然后就是
         * 可以使得模块融合起来，从而实现对应的功能和合并
         */
        /**
         * 注意这里应当有的逻辑就是这个BeanDefinition已经创建过了
         * 所以说这里需要的时候才去创建加载，本质上就是一种懒加载
         */
        return doGetBean(beanName, null);
    }
    public Object getBean(String beanName, Object... args) {
        //这里接收到参数就是会变成一个Object数组
        //这里本质上就是聚合到一起来了
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 同样的这里的获取方法本质上实现的就是从对应的单例注册表中获取对应的Bean对象
     * 但是这里就是通过聚合到一个方法里面实现了，就是对应的doGet方法
     */
    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object bean = getSingleton(beanName);
        if(bean != null) {
            return (T) bean;
        }
        /**
         * 这里的意思是从对应的BeanDefinitionRegistry里面获取对应的BeanDefinition
         * 因为前面是已经注册了
         */
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }
    /**
     * 最基本的情况就是我要使得这个definition的注册表合理起来
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建bean的方法本质上通过Bean的定义类来实现对应的功能
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
