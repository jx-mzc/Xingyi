package com.example.administrator.xingyi.model;

public class News {//动态实体类
    private int _id;//动态ID
    private int adminId;//管理员ID
    private String content;//内容
    private String  time;//发布时间
    private int newsMessageNum;//留言数
    private int newsPraiseNum;//点赞数

    public News(){
        super();
    }

    public News(int _id, int adminId, String content, String time, int newsMessageNum, int newsPraiseNum) {
        this._id = _id;
        this.adminId = adminId;
        this.content = content;
        this.time = time;
        this.newsMessageNum = newsMessageNum;
        this.newsPraiseNum = newsPraiseNum;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNewsMessageNum() {
        return newsMessageNum;
    }

    public void setNewsMessageNum(int newsMessageNum) {
        this.newsMessageNum = newsMessageNum;
    }

    public int getNewsPraiseNum() {
        return newsPraiseNum;
    }

    public void setNewsPraiseNum(int newsPraiseNum) {
        this.newsPraiseNum = newsPraiseNum;
    }


}
