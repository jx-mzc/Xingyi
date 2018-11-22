package com.example.administrator.xingyi.model;

public class Donate {//捐赠实体类
    private int userId;//用户ID
    private int projectId;//公益项目ID
    private int donateStarsNum;//捐赠星星数量
    private String donateTime;//捐赠时间

    public Donate(){
        super();
    }

    public Donate(int userId, int projectId, int donateStarsNum, String donateTime) {
        this.userId = userId;
        this.projectId = projectId;
        this.donateStarsNum = donateStarsNum;
        this.donateTime = donateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDonateStarsNum() {
        return donateStarsNum;
    }

    public void setDonateStarsNum(int donateStarsNum) {
        this.donateStarsNum = donateStarsNum;
    }

    public String getDonateTime() {
        return donateTime;
    }

    public void setDonateTime(String donateTime) {
        this.donateTime = donateTime;
    }
}
