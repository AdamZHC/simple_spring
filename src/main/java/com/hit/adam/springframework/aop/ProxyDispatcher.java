package com.hit.adam.springframework.aop;

//目前就是要实现一个动态代理的功能但是又要解耦开
//先不加方法拦截器
public interface ProxyDispatcher {
    Object getProxyInstance();
}
