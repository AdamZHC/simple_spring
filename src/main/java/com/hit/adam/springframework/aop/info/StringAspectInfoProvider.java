package com.hit.adam.springframework.aop.info;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class StringAspectInfoProvider {
    /**
     * 待解析的字串
     */
    private String matcher;
    /**
     * 目的类对象和目的方法
     */
    private Object targetObject;

    private Method[] targetMethods;
    /**
     * 获取对应的字符串
     */
    public void setMatcher(String matcher) {
        if(matcher == null || matcher.equals("")) {
            this.matcher = matcher;
        }
    }
    public String getMatcher() {
        return this.matcher;
    }
    /**
     * 解析
     */
    public void parse() {
        String className = getMatcher().substring(0, 1);
        try {
            List<Method> methods = new ArrayList<>();
            for(Method method : this.targetObject.getClass().getDeclaredMethods()) {
                if(method.getName().equals(getMatcher().substring(1,2))) {
                    methods.add(method);
                }
            }
            //匿名方法
            this.targetMethods = methods.toArray(new Method[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
