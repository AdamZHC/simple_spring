package com.hit.adam.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.hit.adam.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }
    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
}
