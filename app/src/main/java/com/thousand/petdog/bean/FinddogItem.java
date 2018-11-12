package com.thousand.petdog.bean;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class FinddogItem extends LitePalSupport implements Serializable {
    //发布者用户名
    private String userName;
    //发布者头像
    private int userImg;
    //发布的内容
    private String content;
    //内容图片列表
    private List<Integer> contentImgList;
    //点赞数
    private int thumbs;
    //转发数
    private int forwards;
    //收藏数
    private int collections;
    //评论列表
    private List<ItemCommentBean> commentList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserImg() {
        return userImg;
    }

    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getContentImgList() {
        return contentImgList;
    }

    public void setContentImgList(List<Integer> contentImgList) {
        this.contentImgList = contentImgList;
    }

    public int getThumbs() {
        return thumbs;
    }

    public void setThumbs(int thumbs) {
        this.thumbs = thumbs;
    }

    public int getForwards() {
        return forwards;
    }

    public void setForwards(int forwards) {
        this.forwards = forwards;
    }

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }

    public List<ItemCommentBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ItemCommentBean> commentList) {
        this.commentList = commentList;
    }


    //评论Bean
    public class ItemCommentBean implements Serializable{      //序列化时内部类也必须实现Serializable接口
        //评论者姓名
        private String commmentUser;
        //评论内容
        private String commentContent;
        //评论时间
        private long commentTime;

        public String getCommmentUser() {
            return commmentUser;
        }

        public void setCommmentUser(String commmentUser) {
            this.commmentUser = commmentUser;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }
    }
}
