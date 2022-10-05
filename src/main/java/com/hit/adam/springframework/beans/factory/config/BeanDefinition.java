package com.hit.adam.springframework.beans.factory.config;

import com.hit.adam.springframework.beans.PropertyValues;

/**
 * 这里由之前的Object定义修改成Class
 * 所以说这里也就是不需要自己来传参了
 */
@SuppressWarnings("All")
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 这里不是为了后面有propertyValues的填充所以才会为null的，
     * 主要为了如果传进来的propertyValues为空的话，这样会导致对应的属性为空
     * 但是最好还是加一个空列表的属性值
     */
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

}
