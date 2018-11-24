package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.Clocks;


import java.util.ArrayList;
import java.util.List;


/**
 * Project Name:  Xingyi
 * Date:  2018/11/24 0024
 * Author:  Infinity
 */
public class ClocksDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;

    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  定义构造函数
     */
    public ClocksDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  添加打卡信息
     */
    public void add(Clocks clocks){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("userId", clocks.getUserId());
        values.put("state",clocks.getState());
        values.put("clockDate",clocks.getClockDate());
        db.insert("tb_clocks",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  删除打卡信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_clocks","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  更新打卡信息
     */
    public void update(Clocks clocks){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("userId",clocks.getUserId());
        values.put("state",clocks.getState());
        values.put("clockDate",clocks.getClockDate());
        db.update("tb_clocks",values,"_id = ?",new String[]{String.valueOf(clocks.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  查询单条打卡信息
     */
    public Clocks query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_clocks",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Clocks类中返回
            return new Clocks(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getString(cursor.getColumnIndex("state")),
                    cursor.getString(cursor.getColumnIndex("clockDate")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  查询某个用户的全部打卡信息
     */
    public List<Clocks> getScrollData(int start, int count,int userId){
        List<Clocks> clocksList = new ArrayList<Clocks>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_clocks where userId = ? limit ? offset ?",
                new String[] {String.valueOf(userId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            clocksList.add(new Clocks(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getString(cursor.getColumnIndex("state")),
                    cursor.getString(cursor.getColumnIndex("clockDate"))));
        }
        return clocksList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description:  获取打卡总记录数
     */
    public long getCount() {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_clocks", null);// 获取打卡总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description:  获取某个用户的总打卡记录数
     */
    public long getCount(int userId) {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_clocks where userId = ?",
                new String[]{String.valueOf(userId)});// 获取某个用户的总打卡记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
