package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.Exchange;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/24 0024
 * Author:  Infinity
 */
public class ExchangeDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  定义构造函数
     */
    public ExchangeDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  添加兑换信息
     */
    public void add(Exchange exchange){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("userId", exchange.getUserId());
        values.put("receiver",exchange.getReceiver());
        values.put("tel",exchange.getTel());
        values.put("address",exchange.getAddress());
        values.put("exchangeTime",exchange.getExchangeTime());
        values.put("costStars",exchange.getCostStars());
        values.put("state",exchange.getState());
        db.insert("tb_exchange",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  删除兑换信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_exchange","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  更新兑换信息
     */
    public void update(Exchange exchange){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("userId", exchange.getUserId());
        values.put("receiver",exchange.getReceiver());
        values.put("tel",exchange.getTel());
        values.put("address",exchange.getAddress());
        values.put("exchangeTime",exchange.getExchangeTime());
        values.put("costStars",exchange.getCostStars());
        values.put("state",exchange.getState());
        db.update("tb_exchange",values,"_id = ?",new String[]{String.valueOf(exchange.get_id())});
    }
}
