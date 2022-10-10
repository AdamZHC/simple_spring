package com.hit.adam.springframework.aop.support;

import com.hit.adam.springframework.aop.config.AspectInterceptor;
import com.hit.adam.springframework.aop.info.AspectInfoProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractAspectWireProxyDispatcher extends AbstractProxyDispatcher implements InvocationHandler {



    public InvocationHandler getInvocationHandler() {
        return this;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用被代理对象的方法
//        Object targetObject = getTargetObject();
//        Method[] methods = getMethods();
//        for(Method method_ : methods) {
//            Object result = null;
//            if(method.equals(method_)) {
//                result = aspectInvoke(method, args);
//            } else {
//                result = method.invoke(targetObject, args);
//            }
//            return result;
//        }
        return aspectInvoke(method, args);
    }

//    protected abstract Method[] getMethods();

    protected abstract AspectInterceptor getInterceptor();

    private Object aspectInvoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Object targetObject = getTargetObject();
        getInterceptor().doBefore(targetObject);
        Object result = method.invoke(targetObject, args);
        getInterceptor().doAfter(targetObject);
        return result;
    }
}
