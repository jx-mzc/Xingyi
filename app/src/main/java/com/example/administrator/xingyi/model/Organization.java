package com.example.administrator.xingyi.model;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/24 0024
 * Author:  Infinity
 */
public class Organization {//公益组织实体类
    private int _id;//组织ID
    private int organizationImgRes;//组织图片
    private String organizationName;//组织名称
    private String organizationIntroduction;//组织简介
    private int sponsorNum;//组织发起项目数

    public Organization() {
        super();
    }

    public Organization(int _id, int organizationImgRes, String organizationName, String organizationIntroduction, int sponsorNum) {
        this._id = _id;
        this.organizationImgRes = organizationImgRes;
        this.organizationName = organizationName;
        this.organizationIntroduction = organizationIntroduction;
        this.sponsorNum = sponsorNum;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getOrganizationImgRes() {
        return organizationImgRes;
    }

    public void setOrganizationImgRes(int organizationImgRes) {
        this.organizationImgRes = organizationImgRes;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationIntroduction() {
        return organizationIntroduction;
    }

    public void setOrganizationIntroduction(String organizationIntroduction) {
        this.organizationIntroduction = organizationIntroduction;
    }

    public int getSponsorNum() {
        return sponsorNum;
    }

    public void setSponsorNum(int sponsorNum) {
        this.sponsorNum = sponsorNum;
    }
}
