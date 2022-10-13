package com.hit.adam.test.aop.jdk;

import com.hit.adam.springframework.aop.info.AnnotationAspectInfo;
import com.hit.adam.springframework.aop.support.cglib.DefaultCglibProxyDispatcher;
import com.hit.adam.springframework.aop.support.jdk.DefaultJdkProxyDispatcher;

public class AopTest {
    public static void main(String[] args) {
        /**
         * 创建Info
         */
        AnnotationAspectInfo aspectInfo = new AnnotationAspectInfo();
        aspectInfo.setTargetObject(new Tiger());
        aspectInfo.setMethodAdviser(new TigerProxy());

        DefaultCglibProxyDispatcher proxyDispatcher = new DefaultCglibProxyDispatcher();
        proxyDispatcher.setAspectInfo(aspectInfo);
        Animal proxyInstance = (Animal) proxyDispatcher.getProxyInstance();
        proxyInstance.feed();

    }
}
