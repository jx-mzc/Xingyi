package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.ExchangeDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/25 0025
 * Author:  Infinity
 */
public class ExchangeDetailsDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  定义构造函数
     */
    public ExchangeDetailsDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  添加兑换单明细信息
     */
    public void add(ExchangeDetails exchangeDetails){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("exchangeId",exchangeDetails.getExchangeId());
        values.put("commodityId",exchangeDetails.getCommodityId());
        values.put("commodityName",exchangeDetails.getCommodityName());
        values.put("num",exchangeDetails.getNum());
        db.insert("tb_exchangeDetails",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  删除兑换单明细信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_exchangeDetails","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  更新兑换单明细信息
     */
    public void update(ExchangeDetails exchangeDetails){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("exchangeId",exchangeDetails.getExchangeId());
        values.put("commodityId",exchangeDetails.getCommodityId());
        values.put("commodityName",exchangeDetails.getCommodityName());
        values.put("num",exchangeDetails.getNum());
        db.update("tb_exchangeDetails",values,"_id = ?",new String[]{String.valueOf(exchangeDetails.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询单条兑换单明细信息
     */
    public ExchangeDetails query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_exchangeDetails",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到ExchangeDetails类中返回
            return new ExchangeDetails(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("exchangeId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getInt(cursor.getColumnIndex("num")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询所有兑换单明细信息
     */
    public List<ExchangeDetails> getScrollData(int start, int count){
        List<ExchangeDetails> exchangeDetailsList = new ArrayList<ExchangeDetails>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_exchangeDetails limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            exchangeDetailsList.add(new ExchangeDetails(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("exchangeId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getInt(cursor.getColumnIndex("num"))));
        }
        return exchangeDetailsList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某条兑换单的兑换明细信息
     */
    public List<ExchangeDetails> getCommodityScrollData(int start, int count,int exchangeId){
        List<ExchangeDetails> exchangeDetailsList = new ArrayList<ExchangeDetails>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_exchangeDetails where exchangeId = ? limit ? offset ?",
                new String[] {String.valueOf(exchangeId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            exchangeDetailsList.add(new ExchangeDetails(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("exchangeId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getInt(cursor.getColumnIndex("num"))));
        }
        return exchangeDetailsList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某件商品的兑换明细信息
     */
    public List<ExchangeDetails> getExchangeScrollData(int start, int count,int commodityId){
        List<ExchangeDetails> exchangeDetailsList = new ArrayList<ExchangeDetails>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_exchangeDetails where commodityId = ? limit ? offset ?",
                new String[] {String.valueOf(commodityId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            exchangeDetailsList.add(new ExchangeDetails(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("exchangeId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getInt(cursor.getColumnIndex("num"))));
        }
        return exchangeDetailsList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取兑换明细总记录数
     */
    public long getCount(){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_exchangeDetails",null);// 获取兑换单总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某条兑换单的总兑换明细记录数
     */
    public long getCommodityCount(int exchangeId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_exchangeDetails where exchangeId = ?",
                new String[]{String.valueOf(exchangeId)});// 获取某条兑换单的总兑换明细记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某件商品的总兑换明细记录数
     */
    public long getExchangeCount(int commodityId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_exchangeDetails where commodityId = ?",
                new String[]{String.valueOf(commodityId)});// 获取某件商品的总兑换明细记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
