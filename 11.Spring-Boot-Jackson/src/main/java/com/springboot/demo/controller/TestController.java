package com.springboot.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/20 16:08
 * Email: 494657028@qq.com
 */
@Controller
public class TestController {
    @Autowired
    private ObjectMapper mapper;

    // {"userName":"yutiy","age":0,"password":null,"birthday":"2020-11-20T08:11:56.233+00:00"}
    // JsonConfig -> {"userName":"yutiy","age":0,"password":null,"birthday":"2020-11-20 11:56.23"}
    @ResponseBody
    @RequestMapping("get_user")
    @JsonView(User.AllUserFieldView.class)
    public User getUser() {
        User user = new User();
        user.setUserName("yutiy");
        user.setBirthday(new Date());
        return user;
    }

    // 序列化
    @ResponseBody
    @RequestMapping("serialization")
    public String serialization() {
        try {
            User user = new User();
            user.setUserName("yutiy");
            user.setBirthday(new Date());
            return mapper.writeValueAsString(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 反序列化
    @ResponseBody
    @RequestMapping("readjsonstring")
    public String readJsonString() {
        try {
            String json = "{\"name\":\"yutiy\",\"age\":26}";
            JsonNode node = mapper.readTree(json);
            String name = node.get("name").asText();
            int age = node.get("age").asInt();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 绑定对象反序列化
    @ResponseBody
    @RequestMapping("readjsonasobject")
    public String readJsonAsObject() {
        try {
            String json = "{\"userName\":\"yutiy\",\"age\":26}";
            User user = mapper.readValue(json, User.class);
            String name = user.getUserName();
            int age = user.getAge();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("formatobjecttojsonstring")
    public String formatObjectToJsonString() {
        try {
            User user = new User();
            user.setUserName("yutiy");
            user.setAge(26);
            user.setPassword("123456");
            user.setBirthday(new Date());
            String jsonStr = mapper.writeValueAsString(user);
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 集合序列化
    @ResponseBody
    @RequestMapping("updateuser")
    public int updateUser(@RequestBody List<User> list){
        return list.size();
    }

    // 集合反序列化
    @ResponseBody
    @RequestMapping("customize")
    public String customize() throws JsonParseException, JsonMappingException, IOException {
        String jsonStr = "[{\"userName\":\"yutiy\",\"age\":26},{\"userName\":\"scott\",\"age\":27}]";
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = mapper.readValue(jsonStr, type);
        String msg = "";
        for (User user : list) {
            msg += user.getUserName();
        }
        return msg;
    }
}
