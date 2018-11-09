package com.thousand.petdog.model;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class SortModel extends LitePalSupport implements Serializable {

    private String name;
    private String letters;//显示拼音的首字母

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
}
