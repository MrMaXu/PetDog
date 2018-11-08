package com.thousand.petdog.model;


import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * 创建人：Mir.Ma
 * 时间：2018/11/4 11:08
 * 描述：存储狗的基本信息
 */
public class Dog extends LitePalSupport {
    //姓名 年龄 性别 出生年月
    String dog_name;
    String dog_age;
    String dog_sex;
    java.util.Date   dog_birthday;
    //身高 体重 BMI 温度
    float dog_height;
    float dog_weight;
    float dog_BMI;
    float dog_temperature;

    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public String getDog_age() {
        return dog_age;
    }

    public void setDog_age(String dog_age) {
        this.dog_age = dog_age;
    }

    public String getDog_sex() {
        return dog_sex;
    }

    public void setDog_sex(String dog_sex) {
        this.dog_sex = dog_sex;
    }

    public Date getDog_birthday() {
        return dog_birthday;
    }

    public void setDog_birthday(Date dog_birthday) {
        this.dog_birthday = dog_birthday;
    }

    public float getDog_height() {
        return dog_height;
    }

    public void setDog_height(float dog_height) {
        this.dog_height = dog_height;
    }

    public float getDog_weight() {
        return dog_weight;
    }

    public void setDog_weight(float dog_weight) {
        this.dog_weight = dog_weight;
    }

    public float getDog_BMI() {
        return dog_BMI;
    }

    public void setDog_BMI(float dog_BMI) {
        this.dog_BMI = dog_BMI;
    }

    public float getDog_temperature() {
        return dog_temperature;
    }

    public void setDog_temperature(float dog_temperature) {
        this.dog_temperature = dog_temperature;
    }

    public Dog() {
        super();
    }


    //录入狗的日期
String dog_healthDate;

    public String getDog_healthDate() {
        return dog_healthDate;
    }

    public void setDog_healthDate(String dog_healthDate) {
        this.dog_healthDate = dog_healthDate;
    }
    public Dog(String dog_healthDate){
        this.dog_healthDate=dog_healthDate;
    }
}
