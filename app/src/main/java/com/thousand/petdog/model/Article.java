package com.thousand.petdog.model;

import org.litepal.crud.LitePalSupport;

/**
 * 创建人：Mir.Ma
 * 时间：2018/11/9 16:17
 * 描述：
 */
public class Article extends LitePalSupport {
    String articleTitle;
    String articleData;

    public Article(String articleTitle, String articleData) {
        this.articleTitle = articleTitle;
        this.articleData = articleData;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Article(String articleTitle) {

        this.articleTitle = articleTitle;
    }
}
