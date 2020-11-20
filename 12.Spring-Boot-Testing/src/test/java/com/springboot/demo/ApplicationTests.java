package com.springboot.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.bean.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

@SpringBootTest
class ApplicationTests {
    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        session = new MockHttpSession();
        User user = new User();
        user.setUsername("Dopa");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        user.setStatus("1");
        session.setAttribute("user", user);
    }

    // MockMvc模拟MVC请求
    @Test
    public void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}","yutiy"));
        mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/fileupload").file("file", "文件内容".getBytes("utf-8")));
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("message", "hello"));
        mockMvc.perform(MockMvcRequestBuilders.get("/hobby/save").param("hobby", "sleep", "eat"));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name", "yutiy");
        params.add("hobby", "sleep");
        params.add("hobby", "eat");

        String name = "school";
        String value = "jmu";
        mockMvc.perform(MockMvcRequestBuilders.get("/hobby/save").params(params));
        mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr(name, value));
        mockMvc.perform(MockMvcRequestBuilders.get("/index").cookie(new Cookie(name, value)));
        mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).header(name, value));

        String jsonStr = "{\"username\":\"Dopa\",\"passwd\":\"ac3af72d9f95161a502fd326865c2f15\",\"status\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));

        String userJson = mapper.writeValueAsString(session.getAttribute("user"));
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(userJson.getBytes()));
    }

    // MockMvc处理返回结果
    @Test
    public void test1() throws Exception {
        // 判断Controller方法是否返回某视图
        mockMvc.perform(MockMvcRequestBuilders.post("/index"))
                .andExpect(MockMvcResultMatchers.view().name("index.html"));

        // 比较Model
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1))
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("password"))
                .andExpect(MockMvcResultMatchers.model().attribute("username", "yutiy"));

        // 比较forward或者redirect
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index.html"));
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("index.html"));

        // 比较返回内容，使用content()
        // 返回内容为hello
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.content().string("hello"));
        // 返回内容是XML，并且与xmlContent一样
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.content().xml("xmlContent"));
        // 返回内容是JSON ，并且与jsonContent一样
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.content().json("jsonContent"));
    }

    // 测试Controller
    @Test
    @Transactional
    public void test2() throws Exception {
        // 期望成功调用，即HTTP Status为200 -> 期望返回内容是application/json -> 检查返回JSON数据中某个值的内容 -> 输出响应结果
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{userName}", "scott"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("scott")) // https://github.com/json-path/JsonPath
                .andDo(MockMvcResultHandlers.print());

        User user = new User();
        user.setUsername("Dopa");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        user.setStatus("1");

        String userJson = mapper.writeValueAsString(user);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(userJson.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

//    @BeforeAll
//    public static void beforeClassTest() {
//        System.out.println("before class test");
//    }
//
//    @BeforeEach
//    public void beforeTest() {
//        System.out.println("before test");
//    }
//
//    @Test
//    public void Test1() {
//        System.out.println("test 1+1=2");
//        Assertions.assertEquals(2, 1 + 1);
//    }
//
//    @Test
//    public void Test2() {
//        System.out.println("test 2+2=4");
//        Assertions.assertEquals(4, 2 + 2);
//    }
//
//    @AfterEach
//    public void afterTest() {
//        System.out.println("after test");
//    }
//
//    @AfterAll
//    public static void afterClassTest() {
//        System.out.println("after class test");
//    }
}
