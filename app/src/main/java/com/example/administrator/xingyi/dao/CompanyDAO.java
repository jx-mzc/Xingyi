package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/23 0023
 * Author:  Infinity
 */
public class CompanyDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  定义构造函数
     */
    public CompanyDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  添加企业信息
     */
    public void add(Company company){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("companyName",company.getCompanyName());
        values.put("companyIntroduction",company.getCompanyIntroduction());
        values.put("fundNum",company.getFundNum());
        db.insert("tb_company",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  删除企业信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_company","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  更新企业信息
     */
    public void update(Company company){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("companyName",company.getCompanyName());
        values.put("companyIntroduction",company.getCompanyIntroduction());
        values.put("fundNum",company.getFundNum());
        db.update("tb_company",values,"_id = ?",new String[]{String.valueOf(company.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  查询单条企业信息
     */
    public Company query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_company",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Company类中返回
            return new Company(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("companyName")),
                    cursor.getString(cursor.getColumnIndex("companyIntroduction")),
                    cursor.getInt(cursor.getColumnIndex("fundNum")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description: 查询多条企业信息
     */
    public List<Company> getScrollData(int start, int count){
        List<Company> companyList = new ArrayList<Company>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_company limit ? offset ?",
                new String[] { String.valueOf(count),String.valueOf(start-1) });
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            companyList.add(new Company(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("companyName")),
                    cursor.getString(cursor.getColumnIndex("companyIntroduction")),
                    cursor.getInt(cursor.getColumnIndex("fundNum"))));
        }
        return companyList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/23 0023
     * @Description:  获取总记录数
     */
    public long getCount() {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_company",
                null);// 获取管理员信息的记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
