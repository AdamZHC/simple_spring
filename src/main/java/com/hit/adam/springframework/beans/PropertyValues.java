package com.hit.adam.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 相当于是在这个基础上BeanDefinition的基础上
 * 添加一系列的东西！也就是说我的Bean的定义除了Class
 * 还有对应的PropertyValues, 这里其实比较简单的，因为相当于
 * 其实就是对于一些抽象概念的封装，不是很复杂的
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        /**
         * 这里的添加参数的方法就是昨天看的昨天的泛型方法
         * 因为可以自己识别，所以说一般不会写上去这个<>的泛型定义
         */
        return this.propertyValueList.<PropertyValue>toArray(new PropertyValue[0]);
    }
    public PropertyValue getPropertyValue(String propertyName) {
        for(PropertyValue pv : this.propertyValueList) {
            if(pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
