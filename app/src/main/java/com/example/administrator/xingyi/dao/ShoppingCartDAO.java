package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.exchange.CommodityDetailActivity;
import com.example.administrator.xingyi.model.ShoppingCart;
import com.example.administrator.xingyi.model.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/25 0025
 * Author:  Infinity
 */
public class ShoppingCartDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  定义构造函数
     */
    public ShoppingCartDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  添加购物车信息
     */
    public void add(ShoppingCart shoppingCart){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("userId",shoppingCart.getUserId());
        values.put("commodityId",shoppingCart.getCommodityId());
        values.put("commodityName",shoppingCart.getCommodityName());
        values.put("num",shoppingCart.getNum());
        db.insert("tb_shoppingCart",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  删除购物车信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_shoppingCart","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  更新购物车信息
     */
    public void update(ShoppingCart shoppingCart){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("userId",shoppingCart.getUserId());
        values.put("commodityId",shoppingCart.getCommodityId());
        values.put("commodityName",shoppingCart.getCommodityName());
        values.put("num",shoppingCart.getNum());
        db.update("tb_shoppingCart",values,"_id = ?",new String[]{String.valueOf(shoppingCart.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询单条购物车信息
     */
    public ShoppingCart query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_shoppingCart",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到ShoppingCart类中返回
            return new ShoppingCart(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getInt(cursor.getColumnIndex("num")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询所有购物车信息
     */
    public List<ShoppingCart> getScrollData(int start, int count){
        List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_shoppingCart limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            shoppingCartList.add(new ShoppingCart(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getInt(cursor.getColumnIndex("num"))));
        }
        return shoppingCartList;// 返回集合
    }

    /**
     * @Author:  ting
     * @Date:  2018/12/05
     * @Description:  查询所有购物车信息
     */
    public List<ShoppingCartItem> getCommodityScrollData(int start, int count){
        List<ShoppingCartItem> shoppingCartItemList = new ArrayList<ShoppingCartItem>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select s._id as _id, s.userId as userId, s.commodityId as commodityId, s.commodityName as commodityName, num, commodityIntroduction, commodityStars from tb_shoppingCart as s,tb_commodity as c where s.commodityId=c._id  limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            shoppingCartItemList.add(new ShoppingCartItem(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    CommodityDetailActivity.COMMODITY_IMAGES[cursor.getColumnIndex("commodityId")-1],
                    cursor.getString(cursor.getColumnIndex("commodityIntroduction")),
                    cursor.getInt(cursor.getColumnIndex("commodityStars")),
                    false,
                    cursor.getInt(cursor.getColumnIndex("num"))));
        }
        return shoppingCartItemList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个用户的所有购物车信息
     */
    public List<ShoppingCart> getScrollData(int start, int count,int userId){
        List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_shoppingCart where userId = ? limit ? offset ?",
                new String[] {String.valueOf(userId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            shoppingCartList.add(new ShoppingCart(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getInt(cursor.getColumnIndex("num"))));
        }
        return shoppingCartList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个商品的所有购物车信息
     */
    public List<ShoppingCart> getUserScrollData(int start, int count,int commodityId){
        List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_shoppingCart where commodityId = ? limit ? offset ?",
                new String[] {String.valueOf(commodityId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            shoppingCartList.add(new ShoppingCart(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("commodityId")),
                    cursor.getString(cursor.getColumnIndex("commodityName")),
                    cursor.getInt(cursor.getColumnIndex("num"))));
        }
        return shoppingCartList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取购物车总记录数
     */
    public long getCount(){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_shoppingCart",null);// 获取购物车总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某个用户的购物车总记录数
     */
    public long getCommodityCount(int userId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_shoppingCart where userId = ?",
                new String[]{String.valueOf(userId)});// 获取某个用户的购物车总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某件商品的购物车总记录数
     */
    public long getUserCount(int commodityId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_projectPraise where commodityId = ?",
                new String[]{String.valueOf(commodityId)});// 获取某件商品的购物车总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
