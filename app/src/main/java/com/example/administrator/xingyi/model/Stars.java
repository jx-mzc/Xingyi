package com.example.administrator.xingyi.model;

public class Stars {//星星实体类
    private int _id;//星星ID
    private int userId;//用户ID
    private int donableStars;//可捐星星
    private int donatedStars;//已捐星星
    private int exchangeableStars;//可兑星星
    private int exchangedStars;//已兑星星

    public Stars(){
        super();
    }

    public Stars(int _id, int userId, int donableStars, int donatedStars, int exchangeableStars, int exchangedStars) {
        this._id = _id;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
