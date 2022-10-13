package com.hit.adam.springframework.aop.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 在方法上定义切面
 * 有效时间——运行时仍然有效
 * 这就是定义在方法上面的如果说方法上有这个注解
 * 那么就要执行aop的切面方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pointcut {
    MethodAspectType value() default MethodAspectType.BOTH;
}
