package com.thousand.petdog.bean;

public class NavgationItem {

    int navgationImg;
    String navgationName;

    public NavgationItem(int navgationImg, String navgationName) {
        this.navgationImg = navgationImg;
        this.navgationName = navgationName;
    }

    public int getNavgationImg() {
        return navgationImg;
    }

    public void setNavgationImg(int navgationImg) {
        this.navgationImg = navgationImg;
    }

    public String getNavgationName() {
        return navgationName;
    }

    public void setNavgationName(String navgationName) {
        this.navgationName = navgationName;
    }
}
