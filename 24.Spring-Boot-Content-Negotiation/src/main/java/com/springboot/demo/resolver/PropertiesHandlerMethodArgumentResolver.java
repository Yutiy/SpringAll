package com.springboot.demo.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Author: yutiy
 * Date: 2020/11/24 15:37
 * Email: 494657028@qq.com
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    // 指定支持解析的参数类型，这里为Properties类型
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Properties.class.equals(methodParameter.getParameterType());
    }

    // 实现解析逻辑，解析过程和 PropertiesHttpMessageConverter 的 readInternal 方法类似
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        ServletWebRequest servletWebRequest = (ServletWebRequest) nativeWebRequest;
        HttpServletRequest request = servletWebRequest.getRequest();
        String contentType = request.getHeader("Content-Type");

        MediaType mediaType = MediaType.parseMediaType(contentType);
        // 获取编码
        Charset charset = mediaType.getCharset() == null ? StandardCharsets.UTF_8 : mediaType.getCharset();
        // 获取输入流
        InputStream inputStream = request.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);

        // 输入流转换为 Properties
        Properties properties = new Properties();
        properties.load(inputStreamReader);
        return properties;
    }
}
