package com.hit.adam.springframework.aop.info;

import com.hit.adam.springframework.aop.config.Aspect;
import com.hit.adam.springframework.aop.config.AspectPolisher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationAspectInfo implements AspectInfo{
    /**
     * 被代理的方法
     */
    private Object targetObject;
    /**
     * 对应的拦截器的方法
     * 我这里是实现了一个方法拦截器，也就是说
     * 不一定是所有定义的接口方法都要去执行对应的
     * 切面方法
     */
    private AspectPolisher aspectPolisher;

    private Method[] methods;

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public void setAspectPolisher(AspectPolisher aspectPolisher) {
        this.aspectPolisher = aspectPolisher;
    }

    @Override
    public Method[] getTargetMethods() {
        parseMethod();
        return this.methods;
    }

    @Override
    public AspectPolisher getAspectPolisher() {
        return this.aspectPolisher;
    }

    private void parseMethod() {
        if(methods != null && methods.length == 0)
            return;
        Class<?> clazz = targetObject.getClass();
        List<Method> methods = new ArrayList<>();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if(declaredMethod.getAnnotation(Aspect.class) != null) {
                methods.add(declaredMethod);
            }
        }
        this.methods = methods.toArray(new Method[0]);
    }
}
