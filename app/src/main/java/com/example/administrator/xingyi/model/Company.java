package com.example.administrator.xingyi.model;

public class Company {//企业实体类
    private int _id;//企业ID
    private String companyName;//企业名称
    private String companyIntroduction;//企业简介
    private int fundNum;//资助项目数量

    public Company() {
        super();
    }

    public Company(int _id, String companyName, String companyIntroduction, int fundNum) {
        this._id = _id;
        this.companyName = companyName;
        this.companyIntroduction = companyIntroduction;
        this.fundNum = fundNum;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }

    public int getFundNum() {
        return fundNum;
    }

    public void setFundNum(int fundNum) {
        this.fundNum = fundNum;
    }
}
