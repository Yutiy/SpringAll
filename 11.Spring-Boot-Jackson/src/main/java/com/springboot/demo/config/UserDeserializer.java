package com.springboot.demo.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.demo.bean.User;

import java.io.IOException;

/**
 * Author: yutiy
 * Date: 2020/11/20 16:45
 * Email: 494657028@qq.com
 */
public class UserDeserializer extends JsonDeserializer<User> {
    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String userName = node.get("user_name").asText();
        User user = new User();
        user.setUserName(userName);
        return user;
    }
}
