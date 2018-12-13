package com.example.administrator.xingyi.project.projectMessage;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/13 0013
 * Author:  Infinity
 */
public class ProjectMessageView {
    private int touxiang;
    private String userName;
    private String userMessage;
    private String messageDate;

    public ProjectMessageView(int touxiang, String userName, String userMessage, String messageDate) {
        this.touxiang = touxiang;
        this.userName = userName;
        this.userMessage = userMessage;
        this.messageDate = messageDate;
    }

    public int getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(int touxiang) {
        this.touxiang = touxiang;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }
}
