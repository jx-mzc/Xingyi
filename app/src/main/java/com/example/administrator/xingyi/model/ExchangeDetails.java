package com.example.administrator.xingyi.model;

public class ExchangeDetails {//兑换单明细实体类
    private int _id;//兑换单明细ID
    private int exchangeId;//兑换单ID
    private int commodityId;//商品ID
    private String commodityName;//商品名
    private int num;//数量

    public ExchangeDetails(){
        super();
    }

    public ExchangeDetails(int _id, int exchangeId, int commodityId, String commodityName, int num) {
        this._id = _id;
        this.exchangeId = exchangeId;
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

    public int getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
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
