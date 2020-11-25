package com.springboot.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: yutiy
 * Date: 2020/11/24 23:42
 * Email: 494657028@qq.com
 */
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String description;
}
