package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/24 0024
 * Author:  Infinity
 */
public class OrganizationDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description:  定义构造函数
     */
    public OrganizationDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description:  添加组织信息
     */
    public void add(Organization organization){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("organizationName",organization.getOrganizationName());
        values.put("organizationIntroduction",organization.getOrganizationIntroduction());
        values.put("sponsorNum",organization.getSponsorNum());
        db.insert("tb_organization",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description:  删除组织信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_organization","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description:  更新组织信息
     */
    public void update(Organization organization){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("organizationName",organization.getOrganizationName());
        values.put("organizationIntroduction",organization.getOrganizationIntroduction());
        values.put("sponsorNum",organization.getSponsorNum());
        db.update("tb_organization",values,"_id = ?",new String[]{String.valueOf(organization.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description:  查询单条组织信息
     */
    public Organization query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_organization",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Organization类中返回
            return new Organization(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("organizationName")),
                    cursor.getString(cursor.getColumnIndex("organizationIntroduction")),
                    cursor.getInt(cursor.getColumnIndex("sponsorNum")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description: 查询多条组织信息
     */
    public List<Organization> getScrollData(int start, int count){
        List<Organization> organizationList = new ArrayList<Organization>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_organization limit ? offset ?",
                new String[] { String.valueOf(count),String.valueOf(start-1) });
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            organizationList.add(new Organization(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("organizationName")),
                    cursor.getString(cursor.getColumnIndex("organizationIntroduction")),
                    cursor.getInt(cursor.getColumnIndex("sponsorNum"))));
        }
        return organizationList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0023
     * @Description:  获取总记录数
     */
    public long getCount() {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_organization",
                null);// 获取管理员信息的记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
