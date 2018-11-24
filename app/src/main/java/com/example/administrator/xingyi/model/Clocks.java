package com.example.administrator.xingyi.model;


public class Clocks {//打卡实体类
    private int _id;//打卡ID
    private int userId;//用户ID
    private String state;//打卡状态
    private String clockDate;//打卡日期

    public Clocks(){
        super();
    }

    public Clocks(int _id, int userId, String state, String clockDate) {
        this._id = _id;
        this.userId = userId;
        this.state = state;
        this.clockDate = clockDate;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClockDate() {
        return clockDate;
    }

    public void setClockDate(String clockDate) {
        this.clockDate = clockDate;
    }
}
