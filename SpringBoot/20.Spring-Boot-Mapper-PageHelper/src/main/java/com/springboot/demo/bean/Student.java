package com.springboot.demo.bean;

import javax.persistence.*;

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sno;

    private String name;

    private String sex;

    /**
     * @return sno
     */
    public String getSno() {
        return sno;
    }

    /**
     * @param sno
     */
    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
}