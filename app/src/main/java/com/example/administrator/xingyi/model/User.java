package com.example.administrator.xingyi.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {//用户实体类
    private int _id;//用户ID
    private int userImgRes;//用户头像
    private String name;//用户名
    private String pwd;//用户密码
    private long tel;//手机号
    private String address;//地址
    private String registrationDate;//注册日期
    private int donableStars;//可捐星星
    private int donatedStars;//已捐星星
    private int exchangeableStars;//可兑星星
    private int exchangedStars;//已兑星星

    public User(){
        super();
    }

    public User(int _id, int userImgRes,String name, String pwd, long tel, String address, String registrationDate, int donableStars,
                int donatedStars, int exchangeableStars, int exchangedStars)
    {
        this._id = _id;
        this.userImgRes = userImgRes;
        this.name = name;
        this.pwd = pwd;
        this.tel = tel;
        this.address = address;
        this.registrationDate = registrationDate;
        this.donableStars = donableStars;
        this.donatedStars = donatedStars;
        this.exchangeableStars = exchangeableStars;
        this.exchangedStars = exchangedStars;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserImgRes() {
        return userImgRes;
    }

    public void setUserImgRes(int userImgRes) {
        this.userImgRes = userImgRes;
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

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
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

    public int getDonableStars() {
        return donableStars;
    }

    public void setDonableStars(int donableStars) {
        this.donableStars = donableStars;
    }

    public int getDonatedStars() {
        return donatedStars;
    }

    public void setDonatedStars(int donatedStars) {
        this.donatedStars = donatedStars;
    }

    public int getExchangeableStars() {
        return exchangeableStars;
    }

    public void setExchangeableStars(int exchangeableStars) {
        this.exchangeableStars = exchangeableStars;
    }

    public int getExchangedStars() {
        return exchangedStars;
    }

    public void setExchangedStars(int exchangedStars) {
        this.exchangedStars = exchangedStars;
    }
}
