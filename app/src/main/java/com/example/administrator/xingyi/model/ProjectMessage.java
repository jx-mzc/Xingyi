package com.example.administrator.xingyi.model;

public class ProjectMessage {//项目留言实体类
    private int userId;//用户ID
    private int projectId;//公益项目ID
    private String content;//留言内容
    private String messageTime;//留言时间

    public ProjectMessage(){
        super();
    }

    public ProjectMessage(int userId, int projectId, String content, String messageTime) {
        this.userId = userId;
        this.projectId = projectId;
        this.content = content;
        this.messageTime = messageTime;
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
