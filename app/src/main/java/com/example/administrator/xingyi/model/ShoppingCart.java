package com.example.administrator.xingyi.model;

public class ShoppingCart {//购物车实体类
    private int _id;//购物车ID
    private int userId;//用户ID
    private int commodityId;//商品ID
    private String commodityName;//商品名
    private int num;//数量

    public ShoppingCart(){
        super();
    }

    public ShoppingCart(int _id, int userId, int commodityId, String commodityName, int num) {
        this._id = _id;
        this.userId = userId;
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.num = num;
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

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
