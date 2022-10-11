package com.hit.adam.test.aop;

import com.hit.adam.springframework.aop.info.AnnotationAspectInfo;
import com.hit.adam.springframework.aop.support.DefaultProxyDispatcher;

public class AopTest {
    public static void main(String[] args) {
        /**
         * 创建Info
         */
        AnnotationAspectInfo aspectInfo = new AnnotationAspectInfo();
        aspectInfo.setTargetObject(new Tiger());
        aspectInfo.setAspectPolisher(new TigerProxy());

        DefaultProxyDispatcher proxyDispatcher = new DefaultProxyDispatcher();
        proxyDispatcher.setAspectInfo(aspectInfo);
        Animal proxyInstance = (Animal) proxyDispatcher.getProxyInstance();
        proxyInstance.feed();

    }
}
