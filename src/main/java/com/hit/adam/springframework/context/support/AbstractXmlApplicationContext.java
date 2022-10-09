package com.hit.adam.springframework.context.support;

import com.hit.adam.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.hit.adam.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        //这里才使用Xml的BeanDefinition的操作来初始化对应的BeanDefinitionFactory
        //这里才使用Xml实现

        //就是两个框架的有机融合
        //把beanFactory扔进去，现在啥也没有，通过读取这个资源就实现了
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
                beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }
    protected abstract String[] getConfigLocations();

}
