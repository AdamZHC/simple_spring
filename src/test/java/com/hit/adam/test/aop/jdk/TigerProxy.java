package com.hit.adam.test.aop.jdk;

import com.hit.adam.springframework.aop.config.MethodAdviser;

import java.util.HashMap;
import java.util.Map;

public class TigerProxy implements MethodAdviser {
    @Override
    public Map<String, Object> doBefore(Object targetObject) {
        System.out.println("AspectPolisher开始代理");
        HashMap<String, Object> context = new HashMap<>();
        context.put("Thread", 100);
        return context;
    }

    @Override
    public void doAfter(Map<String, Object> context, Object targetObject) {
        System.out.println(context.get("Thread"));
        System.out.println("AspectPolisher结束代理");
    }
}
