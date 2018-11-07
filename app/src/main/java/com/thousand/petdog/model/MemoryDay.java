package com.thousand.petdog.model;

import org.litepal.LitePal;

import java.io.Serializable;

public class MemoryDay extends LitePal implements Serializable {

    private String name;
    private String content;
    private long date;
    private int color;

    public MemoryDay(String name, String content, long date) {
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getDaytoToday(long today){
        return today - this.date;
    }
}
