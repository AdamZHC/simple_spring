package com.hit.adam.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口
 *
 * 根据本质上抽象为一个资源，然后通过inputStream来实现
 * 资源的获取，实现资源的利用
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
