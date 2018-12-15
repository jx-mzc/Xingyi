package com.example.administrator.xingyi.model;

import java.io.Serializable;

/**
 * Created by DoctorFive on 2018/12/6.
 */

public class ShoppingCartItem implements Serializable {
    private int _id;//购物车项id
    private int user_id;//用户id
    private int commodity_id;//商品id
    private String commodityName;//爱心商品名
    private int imageId;//爱心商品图片
    private String commodityIntroduction;//爱心商品描述
    private int commodityStars;//兑换爱心商品所需积分
    private boolean choosed;//是否被选中
    private int count;//购买数量

    public ShoppingCartItem(){}

    public ShoppingCartItem(int _id, int user_id, int commodity_id, String commodityName, int imageId, String commodityIntroduction, int commodityStars, boolean choosed, int count) {
        this._id = _id;
        this.user_id = user_id;
        this.commodity_id = commodity_id;
        this.commodityName = commodityName;
        this.imageId = imageId;
        this.commodityIntroduction = commodityIntroduction;
        this.commodityStars = commodityStars;
        this.choosed = choosed;
        this.count = count;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
