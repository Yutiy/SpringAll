package com.springboot.demo.bean;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springboot.demo.config.UserDeserializer;
import com.springboot.demo.config.UserSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: yutiy
 * Date: 2020/11/20 16:07
 * Email: 494657028@qq.com
 */
@Data
//@JsonIgnoreProperties({ "password", "age" })
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JsonSerialize(using = UserSerializer.class)
//@JsonDeserialize(using = UserDeserializer.class)
public class User implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;

    public interface UserNameView {};
    public interface AllUserFieldView extends UserNameView {};

    @JsonView(UserNameView.class)
    private String userName;

    @JsonView(AllUserFieldView.class)
    private int age;

    // @JsonIgnore
    @JsonView(AllUserFieldView.class)
    private String password;

    // @JsonProperty("bth")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(AllUserFieldView.class)
    private Date birthday;
}
