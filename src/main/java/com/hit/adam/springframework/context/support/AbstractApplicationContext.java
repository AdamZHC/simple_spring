package com.hit.adam.springframework.context.support;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.hit.adam.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.hit.adam.springframework.beans.factory.config.BeanPostProcessor;
import com.hit.adam.springframework.context.ConfigurableApplicationContext;
import com.hit.adam.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象类放到support包下面
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 这里的操作其实和之前的思路是一样的，其实就是一个强化的作用
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        //刷新BeanFactory也是交给Abstract实现
        refreshBeanFactory();
        //获取所有的可配置Bean
        //注意这里的实现和之前是不一样的
        //我们不需要考虑这个类继承或者实现什么BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        //由用户来编写这个然后我们实现call
        //注意这里的情况，首先调用的Xml实现了beanDefinition的注册，但是这个时候还不够
        //需要调用invokeBeanFactoryPostProcessors修改注册信息

        //同样注册对应的对象通过getBean的方法实现
        //同样的返回的就是实例对象
        invokeBeanFactoryPostProcessors(beanFactory);
        //这里的BeanPostProcessor需要先执行，因为需要调用
        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 下面两个方法有点类似
     * 一个是实例化之后直接执行
     *
     * 也就是说这里的注册BeanPostProcessor Object的功能是通过这个实现的
     * 一个是实例化之后放到对应的bean里面
     *
     * 获得实例类一定是通过创建Bean也就是call createBean方法和单例注册表实现
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
}
