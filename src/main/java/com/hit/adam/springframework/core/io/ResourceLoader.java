package com.hit.adam.springframework.core.io;

/**
 * 同样的这里的依赖也就是依赖于定义函数的依赖
 *
 * 并不是其它的依赖方式
 */
public interface ResourceLoader {

    String CLASS_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
