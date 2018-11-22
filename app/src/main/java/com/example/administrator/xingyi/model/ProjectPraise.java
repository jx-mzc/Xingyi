package com.example.administrator.xingyi.model;

public class ProjectPraise {//项目点赞实体类
    private int userId;//用户ID
    private int projectId;//公益项目ID
    private String praiseTime;//点赞时间

    public ProjectPraise(){
        super();
    }

    public ProjectPraise(int userId, int projectId, String praiseTime) {
        this.userId = userId;
        this.projectId = projectId;
        this.praiseTime = praiseTime;
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
