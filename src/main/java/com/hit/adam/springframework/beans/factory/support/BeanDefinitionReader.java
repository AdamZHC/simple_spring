package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.core.io.Resource;
import com.hit.adam.springframework.core.io.ResourceLoader;

/**
 * 接口：BeanDefinitionReader、抽象类：AbstractBeanDefinitionReader、实现类：
 * XmlBeanDefinitionReader，这三部分内容主要是合理清晰的处理了资源读取后的注
 * 册 Bean 容器操作。接口管定义，抽象类处理非接口功能外的注册 Bean 组件填
 * 充，最终实现类即可只关心具体的业务实现
 */
public interface BeanDefinitionReader {

    /**
     * 上面两个接口一般都是非功能性的方法
     *
     * 但是也并不是说这样的就是绝对的，所以说要注意这一点
     */
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
