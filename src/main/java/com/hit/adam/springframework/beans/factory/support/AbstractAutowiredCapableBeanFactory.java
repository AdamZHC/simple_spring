package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.factory.config.BeanDefinition;

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

    public InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
