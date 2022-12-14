package com.hit.adam.springframework.aop.info;

import com.hit.adam.springframework.aop.config.Pointcut;
import com.hit.adam.springframework.aop.config.MethodAdviser;

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
    private MethodAdviser methodAdviser;

    private Method[] methods;

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public void setMethodAdviser(MethodAdviser methodAdviser) {
        this.methodAdviser = methodAdviser;
    }

    @Override
    public Method[] getTargetMethods() {
        parseMethod();
        return this.methods;
    }

    @Override
    public MethodAdviser getMethodAdviser() {
        return this.methodAdviser;
    }

    private void parseMethod() {
        if(methods != null && methods.length == 0)
            return;
        Class<?> clazz = targetObject.getClass();
        List<Method> methods = new ArrayList<>();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if(declaredMethod.getAnnotation(Pointcut.class) != null) {
                methods.add(declaredMethod);
            }
        }
        this.methods = methods.toArray(new Method[0]);
    }
}
