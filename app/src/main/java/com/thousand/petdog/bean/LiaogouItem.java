package com.thousand.petdog.bean;

public class LiaogouItem {

    private int img;
    private String voiceName;

    public LiaogouItem() {
    }

    public LiaogouItem(int img, String voiceName) {
        this.img = img;
        this.voiceName = voiceName;
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
}
