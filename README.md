# @`Hand Written Spring`

## 核心

本质上就是容器管理对象的获取，`Map`用来做实际的管理

## 难点

`Spring`本质上利用了一系列设计模式，可以说把设计模式运用的十分灵活，同样把面向对象的思想也运用地炉火纯青

#### 拆分`BeanDefinition`和`BeanSingetonRegistry`

初期阅读没有搞懂，后来意识到就是把`extends`和`implements`的思路组合到一起，本质上是在一个地方将这个合了起来，但是工厂类的实现需要借助与单例注册表的实现，方便模块的聚合拆分

另外，对于一些特殊的接口继承的思想，或者说核心方法的抽象也很重要

#### 核心方法接口

​	简单做个说明，核心方法的实现通过接口来封装，本质上是为了抽象最终的方法，下面三个接口都是极为重要的，实现了获取`bean`，注册`beandefinition`的重要方法

- `BeanFactory` 
- `SingletonBeanRegistry`
- `BeanDefinitionRegistry`

#### 抽象类的理解

这里的实现就是介于`interface`和`class`中间的类，这里有一个神操作就是，可以把类的实现分阶段进行，也就是可以在没有实现的时候就调用这个类，因为最终可以保证所有的方法都是被实现过的，因此这样的操作是允许实现的，但是看起来有又特别有操作

#### 加载流程

过程就是先注册，后获得对应的实例类，因此这里的核心是有一个`BeanDefinitionRegistry`注册表和`BeanSingletonObjectRegistry`注册表，也就是说，我们需要先去注册到这个注册表里面，然后等加载的时候实现懒加载

#### 懒加载

通过反射的思路实现懒加载

#### @流程简述

首先有若干的原则：

- 模块拆分：`factroy`和`singleton`
- 供用户调用的方法使用接口来实现
- 尽量在核心实现类里面存放对应的属性值

首先是调用`registerBeanDefinition`方法，实现对于`BeanDefinition`的注册，然后调用`getBean`方法的时候，在`BeanDefiniationRegistry`获得类的`Class`类，之后在`SingletonObjectRegistry`类中获取对应的`Object`对象，如果没有的话就是直接`getBeanDefinition`和`addSingletonObject`两个操作