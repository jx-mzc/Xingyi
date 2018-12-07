package com.example.administrator.xingyi.project;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/4 0004
 * Author:  Infinity
 */
public class ProjectView {
    private int projectId;
    private int projectImg;
    private String projectTitle;
    private long targetStarsCount;
    private long currentStarsCount;
    private long participantCount;

    public ProjectView(int projectId,int projectImg, String projectTitle, long targetStarsCount, long currentStarsCount,long participantCount) {
        this.projectId = projectId;
        this.projectImg = projectImg;
        this.projectTitle = projectTitle;
        this.targetStarsCount = targetStarsCount;
        this.currentStarsCount = currentStarsCount;
        this.participantCount = participantCount;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(int projectImg) {
        this.projectImg = projectImg;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public long getTargetStarsCount() {
        return targetStarsCount;
    }

    public void setTargetStarsCount(long targetStarsCount) {
        this.targetStarsCount = targetStarsCount;
    }

    public long getCurrentStarsCount() {
        return currentStarsCount;
    }

    public void setCurrentStarsCount(long currentStarsCount) {
        this.currentStarsCount = currentStarsCount;
    }

    public long getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(long participantCount) {
        this.participantCount = participantCount;
    }
}
