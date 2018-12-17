package com.example.administrator.xingyi.news;

public class NewsView {
    private int touxiang;
    private String adminName;
    private String time;
    private String newsContent;
    private String newsPraiseNum;

    public NewsView(int touxiang, String adminName, String time, String newsContent, String newsPraiseNum) {
        this.touxiang = touxiang;
        this.adminName = adminName;
        this.time = time;
        this.newsContent = newsContent;
        this.newsPraiseNum = newsPraiseNum;
    }

    public int getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(int touxiang) {
        this.touxiang = touxiang;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsPraiseNum() {
        return newsPraiseNum;
    }

    public void setNewsPraiseNum(String newsPraiseNum) {
        this.newsPraiseNum = newsPraiseNum;
    }
}
