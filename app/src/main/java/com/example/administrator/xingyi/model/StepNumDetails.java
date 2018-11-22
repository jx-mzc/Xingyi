package com.example.administrator.xingyi.model;

public class StepNumDetails {//步数明细实体类
    private int _id;//步数明细ID
    private int stepnumId;//步数ID
    private int stepNum;//步数
    private String timeBlock;//时间段

    public StepNumDetails(){
        super();
    }

    public StepNumDetails(int _id, int stepnumId, int stepNum, String timeBlock) {
        this._id = _id;
        this.stepnumId = stepnumId;
        this.stepNum = stepNum;
        this.timeBlock = timeBlock;
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

    public String getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(String timeBlock) {
        this.timeBlock = timeBlock;
    }
}
