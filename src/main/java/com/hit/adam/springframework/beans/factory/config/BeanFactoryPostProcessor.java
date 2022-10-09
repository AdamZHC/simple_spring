package com.hit.adam.springframework.beans.factory.config;

/**
 * 能看懂，但是自己写不出来，另外就是上一个加的好多class和interface我都看不懂
 *
 * 还有者ConfigureBeanFactory我不是太能看懂
 */
public interface BeanFactoryPostProcessor {
    /**
     * Allows for custom modification of
     * an application context's bean definitions,adapting the
     * bean property values of the context's underlying bean
     * factory.
     */
    void postProcessBeanFactory(ConfigurableBeanFactory beanFactory);
}
