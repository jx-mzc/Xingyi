package com.example.administrator.xingyi.model;

public class Exchange {//兑换单实体类
    private int _id;//兑换单ID
    private int userId;//用户ID
    private String receiver;//收货人
    private int tel;//收货电话
    private String address;//收货地址
    private String exchangeTime;//兑换时间
    private int costStars;//所花费星星
    private String state;//兑换单状态

    public Exchange(){
        super();
    }

    public Exchange(int _id, int userId, String receiver, int tel, String address, String exchangeTime, int costStars, String state) {
        this._id = _id;
        this.userId = userId;
        this.receiver = receiver;
        this.tel = tel;
        this.address = address;
        this.exchangeTime = exchangeTime;
        this.costStars = costStars;
        this.state = state;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public int getCostStars() {
        return costStars;
    }

    public void setCostStars(int costStars) {
        this.costStars = costStars;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
