package com.hit.adam.springframework.beans.factory.config;

/**
 * bean的引用
 * 如果是引用类型的话就直接实现的对应的填充bean的操作
 * 注意这里的beanName的设置可以在之前设置好对应的beanName
 * 也就是说最后要保证是存在的才可以的
 *
 * 这里其实是要看最终的测试结果是不是合理的
 */
public class BeanReference {
    private final String beaName;

    public BeanReference(String beaName) {
        this.beaName = beaName;
    }

    public String getBeanName() {
        return beaName;
    }
}
