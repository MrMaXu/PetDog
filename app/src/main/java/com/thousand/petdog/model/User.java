package com.thousand.petdog.model;

import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

/**
 * 创建人：Mir.Ma
 * 时间：2018/11/4 11:07
 * 描述：储存用户的基本信息
 */
public class User extends LitePalSupport {
    /**
    用户的字段如下：
    id
    name 姓名
    password
    sex
    age
    phone
     */
    private  int id;
    private String name;
    private String password;
    private int age;
    private int phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
