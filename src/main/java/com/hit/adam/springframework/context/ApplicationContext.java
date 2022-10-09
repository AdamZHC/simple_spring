package com.hit.adam.springframework.context;

import com.hit.adam.springframework.beans.factory.ListableBeanFactory;

/**
 * 首先获得这个接口功能——列表化所有的BeanDefinition和BeanObject
 *
 * 注意这里的BeanFactory有两个注册表——BeanDefinitionRegistry和BeanRegistry
 */
public interface ApplicationContext extends ListableBeanFactory {

}
