package com.hit.adam.springframework.simple.aop.demo;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        TigerProxy tigerProxy = new TigerProxy(new Tiger());

        Animal animal = (Animal) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{Animal.class}, tigerProxy);

        animal.feed();

    }
}
