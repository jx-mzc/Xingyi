package com.example.administrator.xingyi.model;

public class StepNumDetails {//步数明细实体类
    private int _id;//步数明细ID
    private int stepnumId;//步数ID
    private int stepNum;//步数
    private int timeBlock;//时间段
    private String date;//日期

    public StepNumDetails(){
        super();
    }

    public StepNumDetails(int _id, int stepnumId, int stepNum, int timeBlock,String date) {
        this._id = _id;
        this.stepnumId = stepnumId;
        this.stepNum = stepNum;
        this.timeBlock = timeBlock;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getStepnumId() {
        return stepnumId;
    }

    public void setStepnumId(int stepnumId) {
        this.stepnumId = stepnumId;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public int getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(int timeBlock) {
        this.timeBlock = timeBlock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
