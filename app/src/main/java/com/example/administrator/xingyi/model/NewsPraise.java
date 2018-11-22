package com.example.administrator.xingyi.model;

public class NewsPraise {//动态点赞实体类
    private int userId;//用户ID
    private int newsId;//动态ID
    private String praiseTime;//动态点赞时间

    public NewsPraise(){
        super();
    }

    public NewsPraise(int userId, int newsId, String praiseTime) {
        this.userId = userId;
        this.newsId = newsId;
        this.praiseTime = praiseTime;
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

    public String getPraiseTime() {
        return praiseTime;
    }

    public void setPraiseTime(String praiseTime) {
        this.praiseTime = praiseTime;
    }
}
