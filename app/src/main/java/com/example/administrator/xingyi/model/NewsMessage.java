package com.example.administrator.xingyi.model;

public class NewsMessage {//动态实体类
    private int userId;//用户ID
    private int newsId;//动态ID
    private String content;//动态留言内容
    private String messageTime;//留言时间

    public NewsMessage() {
        super();
    }

    public NewsMessage(int userId, int newsId, String content, String messageTime) {
        this.userId = userId;
        this.newsId = newsId;
        this.content = content;
        this.messageTime = messageTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
