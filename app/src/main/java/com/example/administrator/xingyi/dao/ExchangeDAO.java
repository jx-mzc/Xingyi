package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.Exchange;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询单条兑换单信息
     */
    public Exchange query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_exchange",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Exchange类中返回
            return new Exchange(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getString(cursor.getColumnIndex("receiver")),
                    cursor.getInt(cursor.getColumnIndex("tel")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("exchangeTime")),
                    cursor.getInt(cursor.getColumnIndex("costStars")),
                    cursor.getString(cursor.getColumnIndex("state")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询所有兑换单信息
     */
    public List<Exchange> getScrollData(int start, int count){
        List<Exchange> exchangeList = new ArrayList<Exchange>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_exchange limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            exchangeList.add(new Exchange(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getString(cursor.getColumnIndex("receiver")),
                    cursor.getInt(cursor.getColumnIndex("tel")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("exchangeTime")),
                    cursor.getInt(cursor.getColumnIndex("costStars")),
                    cursor.getString(cursor.getColumnIndex("state"))));
        }
        return exchangeList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个用户的兑换单
     */
    public List<Exchange> getExchangeScrollData(int start, int count,int userId){
        List<Exchange> exchangeList = new ArrayList<Exchange>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_exchange where userId = ? limit ? offset ?",
                new String[] {String.valueOf(userId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            exchangeList.add(new Exchange(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getString(cursor.getColumnIndex("receiver")),
                    cursor.getInt(cursor.getColumnIndex("tel")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("exchangeTime")),
                    cursor.getInt(cursor.getColumnIndex("costStars")),
                    cursor.getString(cursor.getColumnIndex("state"))));
        }
        return exchangeList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取兑换单总记录数
     */
    public long getCount(){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_exchange",null);// 获取兑换单总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某个用户的某个用户的兑换单总记录数
     */
    public long getExchangeCount(int userId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_exchange where userId = ?",
                new String[]{String.valueOf(userId)});// 获取某个用户的兑换单总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
