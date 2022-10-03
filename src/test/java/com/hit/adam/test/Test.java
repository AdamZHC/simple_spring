package com.hit.adam.test;

import com.hit.adam.springframework.simple.BeanDefinition;
import com.hit.adam.springframework.simple.BeanFactory;

public class Test {
    public static void main(String[] args) {
        /**
         * 创建定义类
         *
         * 以及注册过程
         */
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        BeanFactory beanFactory = new BeanFactory();
        beanFactory.register("UserService", beanDefinition);

        /**
         * 调用过程，也就是通过spring容器获得对应的bean对象
         */
        UserService userService = (UserService) beanFactory.getBean("UserService");
        userService.print();

    }
}
