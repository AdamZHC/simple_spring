package com.hit.adam.springframework.aop.support.cglib;

import com.hit.adam.springframework.aop.AssistanceProxyDispatcher;
import com.hit.adam.springframework.aop.config.MethodAdviser;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class AbstractCglibWireInterceptorProxyDispatcher extends AbstractCglibProxyDispatcher implements MethodInterceptor, AssistanceProxyDispatcher {

    public MethodInterceptor getMethodInterceptor() {
        return this;
    };
    /**
     * sub：cglib生成的代理对象
     * method：被代理对象方法
     * objects：方法入参
     * methodProxy: 代理方法
     */
    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object object = methodProxy.invokeSuper(sub, objects);
        Method[] methods = getTargetMethods();
        for(Method method_ : methods) {
            Object result = null;
            if(method.getName().equals(method_.getName())) {
                result = aspectInvoke(sub, methodProxy, objects);
            } else {
                result = methodProxy.invokeSuper(sub, objects);
            }
            return result;
        }
        return object;
    }

    private Object aspectInvoke(Object sub, MethodProxy methodProxy, Object[] objects) throws Throwable {
        Map<String, Object> params = getMethodAdviser().doBefore(sub);
        Object result = methodProxy.invokeSuper(sub, objects);
        getMethodAdviser().doAfter(params, sub);
        return result;
    }

}
