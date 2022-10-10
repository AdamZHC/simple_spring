package com.hit.adam.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.config.BeanDefinition;
import com.hit.adam.springframework.beans.factory.config.BeanReference;
import com.hit.adam.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.hit.adam.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.hit.adam.springframework.core.io.Resource;
import com.hit.adam.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for(String location : locations)
            loadBeanDefinitions(location);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        /**
         * 功能性的代码
         */
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for(int i = 0; i < childNodes.getLength(); ++i) {
            if(! (childNodes.item(i) instanceof Element))
                continue;
            if("bean".equals(childNodes.item(i).getNodeName()))
                continue;
            Element bean = (Element) childNodes.item(i);
            /**
             * 这里其实指的就是一个东西
             */
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            Class<?> clazz = Class.forName(className);
            //体现优先级
            String beanName = StrUtil.isNotEmpty(id) ? id : name;

            if(StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            for(int j = 0; j < bean.getChildNodes().getLength(); ++j) {
                if(!(bean.getChildNodes().item(j) instanceof Element))
                   continue;
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName()))
                   continue;
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                //根据值对象或者引用对象的存在关系确定哪一个是存在的
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
            }
            if(getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName");
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }

    }
}
