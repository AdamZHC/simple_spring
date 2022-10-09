package com.hit.adam.springframework.beans.factory.config;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.BeanFactory;

/**
 * 这个和之前其实是一样的核心功能使用这个接口来表示
 * 之后的方法再用其它的东西来实现
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
