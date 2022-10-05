package com.hit.adam.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.hit.adam.springframework.beans.PropertyValue;
import com.hit.adam.springframework.beans.PropertyValues;
import com.hit.adam.springframework.beans.factory.config.BeanDefinition;
import com.hit.adam.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 注意到这里可以选择性地去继承
 */
@SuppressWarnings("All")
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    /**
     * 这里确实也是实现的核心
     * 考虑如何去实现对应的方法把这里的空参构造转化一波
     */
    public Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addSingletonObject(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            /**
             * 这里就只找一个构造方法
             * 不能只看长度吧
             */
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        /**
         * 注册的时候就设置PropertyValues
         * 最终实例化的时候需要使用这个实例化
         */
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for(PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            /**
             * 表示说如果说这里是一个引用类型话，那就获取对应的Bean的类型，然后再去实例化，所以说这里
             * 本质上是一个递归函数
             */
            if(value instanceof BeanReference) {
                value = getBean(((BeanReference) value).getBeanName());
            }
            BeanUtil.setFieldValue(bean, name, value);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
