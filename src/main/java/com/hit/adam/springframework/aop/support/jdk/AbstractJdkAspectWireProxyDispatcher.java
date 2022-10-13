package com.hit.adam.springframework.aop.support.jdk;

import com.hit.adam.springframework.aop.AssistanceProxyDispatcher;
import com.hit.adam.springframework.aop.config.MethodAdviser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class AbstractJdkAspectWireProxyDispatcher extends AbstractJdkProxyDispatcher implements InvocationHandler, AssistanceProxyDispatcher {



    public InvocationHandler getInvocationHandler() {
        return this;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用被代理对象的方法
        Object targetObject = getTargetObject();
        Method[] methods = getTargetMethods();
        for(Method method_ : methods) {
            Object result = null;
            if(method.getName().equals(method_.getName())) {
                result = aspectInvoke(method, args);
            } else {
                result = method.invoke(targetObject, args);
            }
            return result;
        }
        return aspectInvoke(method, args);
    }

    private Object aspectInvoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        //执行这个方法肯定是需要的
        Object targetObject = getTargetObject();
        Map<String, Object> params = getMethodAdviser().doBefore(targetObject);
        Object result = method.invoke(targetObject, args);
        getMethodAdviser().doAfter(params, targetObject);
        return result;
    }
}
