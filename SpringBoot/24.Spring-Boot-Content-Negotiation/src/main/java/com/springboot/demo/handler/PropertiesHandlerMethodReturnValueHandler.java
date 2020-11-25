package com.springboot.demo.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Author: yutiy
 * Date: 2020/11/24 15:45
 * Email: 494657028@qq.com
 *
 * 方法返回值解析器，用于解析由@RequestMapping注解（或其派生的注解）所标注的方法的返回值
 */
public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return Properties.class.equals(methodParameter.getParameterType());
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        Properties properties = (Properties) o;

        ServletWebRequest servletWebRequest = (ServletWebRequest) nativeWebRequest;

        HttpServletResponse response = servletWebRequest.getResponse();
        ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);

        // 获取请求头
        HttpHeaders headers = servletServerHttpResponse.getHeaders();
        MediaType contentType = headers.getContentType();

        // 获取编码
        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
        }

        // 获取请求体
        OutputStream body = servletServerHttpResponse.getBody();
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(body, charset);

        properties.store(outputStreamWriter, "Serialized by PropertiesHandlerMethodReturnValueHandler#handleReturnValue");

        // 告诉 Spring MVC 请求已经处理完毕
        modelAndViewContainer.setRequestHandled(true);
    }
}
