package com.example.administrator.xingyi.model;

import java.util.Date;

public class StepNum {//步数实体类
    private int _id;//步数ID
    private int userId;//用户ID
    private int stepNums;//总步数
    private String stepDate;//日期

    public StepNum(){
        super();
    }

    public StepNum(int _id, int userId, int stepNums, String stepDate) {
        this._id = _id;
        this.userId = userId;
        this.stepNums = stepNums;
        this.stepDate = stepDate;
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

    public int getStepNums() {
        return stepNums;
    }

    public void setStepNums(int stepNums) {
        this.stepNums = stepNums;
    }

    public String getStepDate() {
        return stepDate;
    }

    public void setStepDate(String stepDate) {
        this.stepDate = stepDate;
    }
}
