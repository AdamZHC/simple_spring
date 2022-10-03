package com.hit.adam.springframework.beans.factory.config;

/**
 * 这里由之前的Object定义修改成Class
 * 所以说这里也就是不需要自己来传参了
 */
@SuppressWarnings("All")
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }


}
