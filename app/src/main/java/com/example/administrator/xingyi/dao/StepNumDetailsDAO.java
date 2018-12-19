package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.StepNumDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/25 0025
 * Author:  Infinity
 */
public class StepNumDetailsDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  定义构造函数
     */
    public StepNumDetailsDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  添加步数明细信息
     */
    public void add(StepNumDetails stepNumDetails){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("stepnumId", stepNumDetails.getStepnumId());
        values.put("stepNum",stepNumDetails.getStepNum());
        values.put("timeBlock",stepNumDetails.getTimeBlock());
        values.put("stepDate",stepNumDetails.getDate());
        db.insert("tb_stepNumDetails",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  删除步数明细信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_stepNumDetails","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  更新步数明细信息
     */
    public void update(StepNumDetails stepNumDetails){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("stepnumId", stepNumDetails.getStepnumId());
        values.put("stepNum",stepNumDetails.getStepNum());
        values.put("timeBlock",stepNumDetails.getTimeBlock());
        values.put("stepDate",stepNumDetails.getDate());
        db.update("tb_stepNumDetails",values,"_id = ?",new String[]{String.valueOf(stepNumDetails.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询单条步数明细信息
     */
    public StepNumDetails query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_stepNumDetails",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到StepNumDetails类中返回
            return new StepNumDetails(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("stepnumId")),
                    cursor.getInt(cursor.getColumnIndex("stepNum")),
                    cursor.getInt(cursor.getColumnIndex("timeBlock")),
                    cursor.getString(cursor.getColumnIndex("stepDate")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/18 0018
     * @Description:  查询某个用户某一天步数明细信息
     */
    public List<StepNumDetails> getScrollData(int stepnumId,String stepDate){
        List<StepNumDetails> stepNumDetailsList = new ArrayList<StepNumDetails>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_stepNumDetails where stepnumId = ? and stepDate = ? order by timeBlock asc",
                new String[] {String.valueOf(stepnumId),stepDate});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            stepNumDetailsList.add(new StepNumDetails(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("stepnumId")),
                    cursor.getInt(cursor.getColumnIndex("stepNum")),
                    cursor.getInt(cursor.getColumnIndex("timeBlock")),
                    cursor.getString(cursor.getColumnIndex("stepDate"))));
        }
        return stepNumDetailsList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询多条步数明细信息
     */
    public List<StepNumDetails> getScrollData(int start, int count){
        List<StepNumDetails> stepNumDetailsList = new ArrayList<StepNumDetails>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_stepNumDetails limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            stepNumDetailsList.add(new StepNumDetails(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("stepnumId")),
                    cursor.getInt(cursor.getColumnIndex("stepNum")),
                    cursor.getInt(cursor.getColumnIndex("timeBlock")),
                    cursor.getString(cursor.getColumnIndex("stepDate"))));
        }
        return stepNumDetailsList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个步数的多条步数明细信息
     */
    public List<StepNumDetails> getStepNumScrollData(int start, int count,int stepnumId){
        List<StepNumDetails> stepNumDetailsList = new ArrayList<StepNumDetails>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_stepNumDetails where stepnumId = ? limit ? offset ?",
                new String[] {String.valueOf(stepnumId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            stepNumDetailsList.add(new StepNumDetails(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("stepnumId")),
                    cursor.getInt(cursor.getColumnIndex("stepNum")),
                    cursor.getInt(cursor.getColumnIndex("timeBlock")),
                    cursor.getString(cursor.getColumnIndex("stepDate"))));
        }
        return stepNumDetailsList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取步数明细总记录数
     */
    public long getCount() {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_stepNumDetails", null);// 获取步数明细总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某个步数的步数明细总记录数
     */
    public long getStepNumCount(int stepnumId) {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_stepNumDetails where stepnumId = ?",
                new String[]{String.valueOf(stepnumId)});// 获取某个步数的步数明细总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
