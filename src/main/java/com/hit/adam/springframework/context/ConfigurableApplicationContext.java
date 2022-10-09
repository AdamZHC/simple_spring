package com.hit.adam.springframework.context;

import com.hit.adam.springframework.beans.BeansException;

/**
 * 通过这个可配置的接口表明可以实现对应的刷新操作
 * 从而实现新的bean的定义或者说初始化
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh() throws BeansException;
}
