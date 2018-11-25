package com.example.administrator.xingyi.model;

public class ProjectPraise {//项目点赞实体类
    private int _id;//项目点赞ID
    private int userId;//用户ID
    private int projectId;//公益项目ID
    private String praiseTime;//点赞时间

    public ProjectPraise(){
        super();
    }

    public ProjectPraise(int _id, int userId, int projectId, String praiseTime) {
        this._id = _id;
        this.userId = userId;
        this.projectId = projectId;
        this.praiseTime = praiseTime;
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

    public String getPraiseTime() {
        return praiseTime;
    }

    public void setPraiseTime(String praiseTime) {
        this.praiseTime = praiseTime;
    }
}
