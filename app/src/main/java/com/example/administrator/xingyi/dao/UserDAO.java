package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/23 0023
 * Author:  Infinity
 */
public class UserDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;

    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  定义构造函数
     */
    public UserDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  添加用户信息
     */
    public void add(User user){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("name",user.getName());
        values.put("pwd",user.getPwd());
        values.put("tel",user.getTel());
        values.put("address",user.getAddress());
        values.put("registrationDate",user.getRegistrationDate());
        values.put("donableStars",user.getDonableStars());
        values.put("donatedStars",user.getDonatedStars());
        values.put("exchangeableStars",user.getExchangeableStars());
        values.put("exchangedStars",user.getExchangedStars());
        db.insert("tb_user",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  删除用户信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_user","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  更新用户信息
     */
    public void update(User user){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("name",user.getName());
        values.put("pwd",user.getPwd());
        values.put("tel",user.getTel());
        values.put("address",user.getAddress());
        values.put("registrationDate",user.getRegistrationDate());
        values.put("donableStars",user.getDonableStars());
        values.put("donatedStars",user.getDonatedStars());
        values.put("exchangeableStars",user.getExchangeableStars());
        values.put("exchangedStars",user.getExchangedStars());
        db.update("tb_user",values,"_id = ?",new String[]{String.valueOf(user.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  查询单条用户信息
     */
    public User query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_user",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到User类中返回
            return new User(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("pwd")),
                    cursor.getInt(cursor.getColumnIndex("tel")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("registrationDate")),
                    cursor.getInt(cursor.getColumnIndex("donableStars")),
                    cursor.getInt(cursor.getColumnIndex("donatedStars")),
                    cursor.getInt(cursor.getColumnIndex("exchangeableStars")),
                    cursor.getInt(cursor.getColumnIndex("exchangedStars")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description: 查询多条用户信息
     */
    public List<User> getScrollData(int start, int count){
        List<User> userList = new ArrayList<User>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_user limit ? offset ?",
                new String[] { String.valueOf(count),String.valueOf(start-1) });
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            userList.add(new User(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("pwd")),
                    cursor.getInt(cursor.getColumnIndex("tel")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("registrationDate")),
                    cursor.getInt(cursor.getColumnIndex("donableStars")),
                    cursor.getInt(cursor.getColumnIndex("donatedStars")),
                    cursor.getInt(cursor.getColumnIndex("exchangeableStars")),
                    cursor.getInt(cursor.getColumnIndex("exchangedStars"))));
        }
        return userList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  获取总记录数
     */
    public long getCount() {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_user",
                null);// 获取管理员信息的记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
