package com.thousand.petdog.bean;

import java.io.Serializable;

/**
 * 创建人：Mir.Ma
 * 时间：2018/11/6 16:12
 * 描述：
 */
public class AddressBean implements Serializable {
    private double longitude;//经度
    private double latitude;//纬度
    private String title;//信息标题
    private String text;//信息内容
    private String address;

    public AddressBean(double longitude, double latitude, String title, String text, String address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.title = title;
        this.text = text;
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getAddress() {
        return address;
    }
}
