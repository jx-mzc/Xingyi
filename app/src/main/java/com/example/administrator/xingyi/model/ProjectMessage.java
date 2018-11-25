package com.example.administrator.xingyi.model;

public class ProjectMessage {//项目留言实体类
    private int _id;//项目留言ID
    private int userId;//用户ID
    private int projectId;//公益项目ID
    private String content;//留言内容
    private String messageTime;//留言时间

    public ProjectMessage(){
        super();
    }

    public ProjectMessage(int _id, int userId, int projectId, String content, String messageTime) {
        this._id = _id;
        this.userId = userId;
        this.projectId = projectId;
        this.content = content;
        this.messageTime = messageTime;
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
