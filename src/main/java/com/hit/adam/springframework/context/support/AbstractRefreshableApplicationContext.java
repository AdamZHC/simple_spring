package com.hit.adam.springframework.context.support;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.hit.adam.springframework.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        //这里还没有开始加载对应的BeanDefinition的信息
        //这里其实是默认的用户的操作
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        //这里才开始load
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
