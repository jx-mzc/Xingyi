package com.example.administrator.xingyi.model;

public class Donate {//捐赠实体类
    private int _id;//捐赠ID
    private int userId;//用户ID
    private int projectId;//公益项目ID
    private int donateStarsNum;//捐赠星星数量
    private String donateTime;//捐赠时间

    public Donate(){
        super();
    }

    public Donate(int _id, int userId, int projectId, int donateStarsNum, String donateTime) {
        this._id = _id;
        this.userId = userId;
        this.projectId = projectId;
        this.donateStarsNum = donateStarsNum;
        this.donateTime = donateTime;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
