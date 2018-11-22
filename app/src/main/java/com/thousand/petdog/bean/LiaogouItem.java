package com.thousand.petdog.bean;

public class LiaogouItem {

    private int img;
    private String voiceName;
    private int goujiao;

    public LiaogouItem() {
    }

    public LiaogouItem(int img, String voiceName,int goujiao) {
        this.img = img;
        this.voiceName = voiceName;
        this.goujiao = goujiao;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    public int getGoujiao() {
        return goujiao;
    }

    public void setGoujiao(int goujiao) {
        this.goujiao = goujiao;
    }
}
