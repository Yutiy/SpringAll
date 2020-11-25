package com.springboot.demo.bean;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Author: yutiy
 * Date: 2020/11/24 12:02
 * Email: 494657028@qq.com
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2731598327208972274L;

    @NotBlank(message = "{required}")
    private String name;

    @Email(message = "{invalid}")
    private String email;
}
