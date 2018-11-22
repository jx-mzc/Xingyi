package com.example.administrator.xingyi.model;

import java.util.Date;

public class User {//用户实体类
    private int _id;//用户ID
    private String name;//用户名
    private String pwd;//用户密码
    private int tel;//手机号
    private String address;//地址
    private String registrationDate;//注册日期

    public User(){
        super();
    }

    public User(int _id, String name, String pwd, int tel, String address, String registrationDate) {
        this._id = _id;
        this.name = name;
        this.pwd = pwd;
        this.tel = tel;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
