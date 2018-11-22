package com.example.administrator.xingyi.model;

public class Project {//公益项目实体类
    private int _id;//公益项目ID
    private int adminId;//管理员ID
    private String projectName;//公益项目名
    private String projectIntroduction;//公益项目简述
    private String projectUse;//公益项目用途
    private int companyId;//资助企业ID
    private String projectSponser;//公益项目资助方
    private String projectOriginator;//公益项目发起方
    private int projectNeed;//公益项目所需星星
    private int projectHave;//公益项目已筹集的星星
    private int projectMessageNum;//公益项目留言数
    private int projectPraiseNum;//公益项目点赞数

    public Project(){
        super();
    }

    public Project(int _id,int adminId, String projectName,String projectIntroduction,String projectUse,int companyId, String projectSponser,
                   String projectOriginator,int projectNeed,int projectHave,int projectMessageNum,int projectPraiseNum){
        this._id = _id;
        this.adminId = adminId;
        this.projectName = projectName;
        this.projectIntroduction = projectIntroduction;
        this.projectUse = projectUse;
        this.companyId = companyId;
        this.projectSponser = projectSponser;
        this.projectOriginator = projectOriginator;
        this.projectNeed = projectNeed;
        this.projectHave = projectHave;
        this.projectMessageNum = projectMessageNum;
        this.projectPraiseNum = projectPraiseNum;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIntroduction() {
        return projectIntroduction;
    }

    public void setProjectIntroduction(String projectIntroduction) {
        this.projectIntroduction = projectIntroduction;
    }

    public String getProjectUse() {
        return projectUse;
    }

    public void setProjectUse(String projectUse) {
        this.projectUse = projectUse;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getProjectSponser() {
        return projectSponser;
    }

    public void setProjectSponser(String projectSponser) {
        this.projectSponser = projectSponser;
    }

    public String getProjectOriginator() {
        return projectOriginator;
    }

    public void setProjectOriginator(String projectOriginator) {
        this.projectOriginator = projectOriginator;
    }

    public int getProjectNeed() {
        return projectNeed;
    }

    public void setProjectNeed(int projectNeed) {
        this.projectNeed = projectNeed;
    }

    public int getProjectHave() {
        return projectHave;
    }

    public void setProjectHave(int projectHave) {
        this.projectHave = projectHave;
    }

    public int getProjectMessageNum() {
        return projectMessageNum;
    }

    public void setProjectMessageNum(int projectMessageNum) {
        this.projectMessageNum = projectMessageNum;
    }

    public int getProjectPraiseNum() {
        return projectPraiseNum;
    }

    public void setProjectPraiseNum(int projectPraiseNum) {
        this.projectPraiseNum = projectPraiseNum;
    }
}
