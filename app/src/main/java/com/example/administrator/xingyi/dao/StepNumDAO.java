package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.StepNum;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/25 0025
 * Author:  Infinity
 */
public class StepNumDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  定义构造函数
     */
    public StepNumDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  添加步数信息
     */
    public void add(StepNum stepNum){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("userId", stepNum.getUserId());
        values.put("stepNums",stepNum.getStepNums());
        values.put("stepDate",stepNum.getStepDate());
        db.insert("tb_stepNum",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  删除步数信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_stepNum","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  更新步数信息
     */
    public void update(StepNum stepNum){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("userId", stepNum.getUserId());
        values.put("stepNums",stepNum.getStepNums());
        values.put("stepDate",stepNum.getStepDate());
        db.update("tb_stepNum",values,"_id = ?",new String[]{String.valueOf(stepNum.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询单条步数信息
     */
    public StepNum query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_stepNum",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到StepNum类中返回
            return new StepNum(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("stepNums")),
                    cursor.getString(cursor.getColumnIndex("stepDate")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/17 0017
     * @Description:  根据用户ID和日期查询单条步数信息
     */
    public StepNum query(int userId,String stepDate){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_stepNum",null,"userId = ? and stepDate = ?",new String[]{String.valueOf(userId),stepDate},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到StepNum类中返回
            return new StepNum(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("stepNums")),
                    cursor.getString(cursor.getColumnIndex("stepDate")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询多条步数信息
     */
    public List<StepNum> getScrollData(int start, int count){
        List<StepNum> stepNumList = new ArrayList<StepNum>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_stepNum limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            stepNumList.add(new StepNum(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("stepNums")),
                    cursor.getString(cursor.getColumnIndex("stepDate"))));
        }
        return stepNumList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个用户的多条步数信息
     */
    public List<StepNum> getStepNumScrollData(int start, int count,int userId){
        List<StepNum> stepNumList = new ArrayList<StepNum>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_stepNum where userId = ? limit ? offset ?",
                new String[] {String.valueOf(userId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            stepNumList.add(new StepNum(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("stepNums")),
                    cursor.getString(cursor.getColumnIndex("stepDate"))));
        }
        return stepNumList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取步数总记录数
     */
    public long getCount() {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_stepNum", null);// 获取步数总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某个用户步数总记录数
     */
    public long getStepNumCount(int userId) {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_stepNum where userId = ?",
                new String[]{String.valueOf(userId)});// 获取某个用户步数总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
