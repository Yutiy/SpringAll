package com.springboot.demo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.springboot.demo.bean.User;

import java.io.IOException;

/**
 * Author: yutiy
 * Date: 2020/11/20 16:41
 * Email: 494657028@qq.com
 */
public class UserSerializer extends JsonSerializer<User> {
    // {"user-name":"yutiy"}
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("user-name", user.getUserName());
        jsonGenerator.writeEndObject();
    }
}
