package com.example.administrator.xingyi.model;

import java.io.Serializable;

public class Commodity implements Serializable {//爱心商品实体类
    private int _id;//爱心商品ID
    private String commodityName;//爱心商品名
    private int imageId;//爱心商品图片
    private String commodityIntroduction;//爱心商品描述
    private int commodityStars;//兑换爱心商品所需积分

    public Commodity(){
        super();
    }

    public Commodity(String commodityName, int imageId, String commodityIntroduction, int commodityStars) {
        this.commodityName = commodityName;
        this.imageId = imageId;
        this.commodityIntroduction = commodityIntroduction;
        this.commodityStars = commodityStars;
    }

    public Commodity(int _id, String commodityName, String commodityIntroduction, int commodityStars) {
        this._id = _id;
        this.commodityName = commodityName;
        this.commodityIntroduction = commodityIntroduction;
        this.commodityStars = commodityStars;
    }


    public Commodity(int _id, String commodityName, int imageId, String commodityIntroduction, int commodityStars) {
        this._id = _id;
        this.commodityName = commodityName;
        this.imageId = imageId;
        this.commodityIntroduction = commodityIntroduction;
        this.commodityStars = commodityStars;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityIntroduction() {
        return commodityIntroduction;
    }

    public void setCommodityIntroduction(String commodityIntroduction) {
        this.commodityIntroduction = commodityIntroduction;
    }

    public int getCommodityStars() {
        return commodityStars;
    }

    public void setCommodityStars(int commodityStars) {
        this.commodityStars = commodityStars;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
