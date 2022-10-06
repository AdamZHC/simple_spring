package com.hit.adam.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认的实现类
 */
public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must be null");
        /**
         * 如果是类资源的话，那就使用这个ClassPath
         * 也就是说如果以这个开头的话，startsWith整体上来说比较好
         */
        if(location.startsWith(CLASS_URL_PREFIX)) {
            /**
             * 注意这里的意思就是说去掉classpath:,只获取后面的字符串，因此会使用substring的api
             */
            return new ClassPathResource(location.substring(CLASS_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
