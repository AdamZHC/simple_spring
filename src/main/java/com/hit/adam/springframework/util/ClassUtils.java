package com.hit.adam.springframework.util;

public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {

        }
        if(cl == null) {
            cl = ClassLoader.class.getClassLoader();
        }
        return cl;
    }
}
