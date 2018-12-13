package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.exchange.CommodityDetailActivity;
import com.example.administrator.xingyi.model.Commodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/23 0023
 * Author:  Infinity
 */
public class CommodityDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  定义构造函数
     */
    public CommodityDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  添加商品信息
     */
    public void add(Commodity commodity){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("commodityImgRes",commodity.getCommodityImgRes());
        values.put("commodityName",commodity.getCommodityName());
        values.put("commodityIntroduction",commodity.getCommodityIntroduction());
        values.put("commodityStars",commodity.getCommodityStars());
        db.insert("tb_commodity",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  删除商品信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_commodity","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  更新商品信息
     */
    public void update(Commodity commodity){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("commodityImgRes",commodity.getCommodityImgRes());
        values.put("commodityName",commodity.getCommodityName());
        values.put("commodityIntroduction",commodity.getCommodityIntroduction());
        values.put("commodityStars",commodity.getCommodityStars());
        db.update("tb_commodity",values,"_id = ?",new String[]{String.valueOf(commodity.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  查询单条商品信息
     */
    public Commodity query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_commodity",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Commodity类中返回
            return new Commodity(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("commodityImgRes")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getString(cursor.getColumnIndex("commodityIntroduction")),
                    cursor.getInt(cursor.getColumnIndex("commodityStars")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:查询多条商品信息
     */
    public List<Commodity> getScrollData(int start, int count){
        List<Commodity> commodityList = new ArrayList<Commodity>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_commodity limit ? offset ?",
                new String[] {String.valueOf(count), String.valueOf(start-1) });
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            commodityList.add(new Commodity(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("commodityImgRes")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    CommodityDetailActivity.COMMODITY_IMAGES[cursor.getInt(cursor.getColumnIndex("_id"))-1],
                    cursor.getString(cursor.getColumnIndex("commodityIntroduction")),
                    cursor.getInt(cursor.getColumnIndex("commodityStars"))));
        }
        return commodityList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  获取总记录数
     */
    public long getCount() {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_commodity",
                null);// 获取管理员信息的记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
