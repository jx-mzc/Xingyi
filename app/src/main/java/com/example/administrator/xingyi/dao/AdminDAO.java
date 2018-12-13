package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.Admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/23 0023
 * Author:  Infinity
 */
public class AdminDAO {//管理员类数据访问层对象
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;

    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  定义构造函数
     */
    public AdminDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:添加管理员信息
     */
    public void add(Admin admin){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("name",admin.getName());
        values.put("pwd",admin.getPwd());
        values.put("permission",admin.getPermission());
        db.insert("tb_admin",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  删除管理员信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_admin","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  更新管理员信息
     */
    public void update(Admin admin){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("name",admin.getName());
        values.put("pwd",admin.getPwd());
        values.put("permission",admin.getPermission());
        db.update("tb_admin",values,"_id = ?",new String[]{String.valueOf(admin.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  查询单条管理员信息
     */
    public Admin query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_admin",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Admin类中返回
            return new Admin(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("pwd")),
                    cursor.getString(cursor.getColumnIndex("permission")));
            }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/7 0007
     * @Description:  通过管理员名查找信息是否存在
     */
    public Boolean query(String adminName){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_admin",null,"name = ? ",new String[]{adminName},
                null,null,null);
        if (cursor.moveToNext()){
            return true;
        }
        return false;
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/7 0007
     * @Description:  通过用户名和密码查询单条管理员信息
     */
    public Admin query(String adminName,String pwd){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_admin",null,"name = ? and pwd = ?",new String[]{adminName, pwd},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Admin类中返回
            return new Admin(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("pwd")),
                    cursor.getString(cursor.getColumnIndex("permission")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  查询多条管理员信息
     */
    public List<Admin> getScrollData(int start, int count){
        List<Admin> adminList = new ArrayList<Admin>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_admin limit ? offset ?",
                new String[] { String.valueOf(count),String.valueOf(start-1) });
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            adminList.add(new Admin(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("pwd")),
                    cursor.getString(cursor.getColumnIndex("permission"))));
        }
        return adminList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  获取总记录数
     */
    public long getCount() {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_admin",
                null);// 获取管理员信息的记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
