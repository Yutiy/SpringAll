package com.springboot.demo.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


/**
 * Author: yutiy
 * Date: 2020/11/24 14:31
 * Email: 494657028@qq.com
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {
    // 要在其构造器中指定它能够处理的媒体类型
    public PropertiesHttpMessageConverter() {
        super(new MediaType("text", "properties"));
    }

    // 序列化过程，将响应序列化
    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = httpOutputMessage.getHeaders();
        MediaType contentType = headers.getContentType();

        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
        }

        // 获取请求体
        OutputStream body = httpOutputMessage.getBody();
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(body, charset);

        properties.store(outputStreamWriter, "Serialized by PropertiesHttpMessageConverter#writeInternal");
    }

    // 反序列化过程，即将HTTP请求反序列化为参数的过程
    @Override
    protected Properties readInternal(Class<? extends Properties> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Properties properties = new Properties();
        // 获取请求头
        HttpHeaders headers = httpInputMessage.getHeaders();
        // 获取 content-type
        MediaType contentType = headers.getContentType();
        // 获取编码
        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
        }

        // 获取请求体
        InputStream body = httpInputMessage.getBody();
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        InputStreamReader inputStreamReader = new InputStreamReader(body, charset);

        properties.load(inputStreamReader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, httpInputMessage);
    }
}
