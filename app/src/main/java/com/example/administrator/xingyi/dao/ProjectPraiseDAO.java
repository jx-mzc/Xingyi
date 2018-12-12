package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.ProjectPraise;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/25 0025
 * Author:  Infinity
 */
public class ProjectPraiseDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  定义构造函数
     */
    public ProjectPraiseDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  添加项目点赞信息
     */
    public void add(ProjectPraise projectPraise){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("userId",projectPraise.getUserId());
        values.put("projectId",projectPraise.getProjectId());
        values.put("praiseTime",projectPraise.getPraiseTime());
        db.insert("tb_projectPraise",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  删除项目点赞信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_projectPraise","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  更新项目点赞信息
     */
    public void update(ProjectPraise projectPraise){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("userId",projectPraise.getUserId());
        values.put("projectId",projectPraise.getProjectId());
        values.put("praiseTime",projectPraise.getPraiseTime());
        db.update("tb_projectPraise",values,"_id = ?",new String[]{String.valueOf(projectPraise.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询单条项目点赞信息
     */
    public ProjectPraise query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_projectPraise",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到ProjectPraise类中返回
            return new ProjectPraise(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getString(cursor.getColumnIndex("praiseTime")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/9 0009
     * @Description:  通过用户ID和项目ID查询单条点赞记录
     */
    public ProjectPraise query(int userId,int projectId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_projectPraise",null,"userId = ? and projectId = ?",
                new String[]{String.valueOf(userId),String.valueOf(projectId)}, null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到ProjectPraise类中返回
            return new ProjectPraise(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getString(cursor.getColumnIndex("praiseTime")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询所有项目点赞信息
     */
    public List<ProjectPraise> getScrollData(int start, int count){
        List<ProjectPraise> projectPraiseList = new ArrayList<ProjectPraise>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_projectPraise limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            projectPraiseList.add(new ProjectPraise(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getString(cursor.getColumnIndex("praiseTime"))));
        }
        return projectPraiseList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个用户的所有项目点赞信息
     */
    public List<ProjectPraise> getProjectScrollData(int start, int count,int userId){
        List<ProjectPraise> projectPraiseList = new ArrayList<ProjectPraise>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_projectPraise where userId = ? limit ? offset ?",
                new String[] {String.valueOf(userId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            projectPraiseList.add(new ProjectPraise(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getString(cursor.getColumnIndex("praiseTime"))));
        }
        return projectPraiseList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个项目的所有项目点赞信息
     */
    public List<ProjectPraise> getUserScrollData(int start, int count,int projectId){
        List<ProjectPraise> projectPraiseList = new ArrayList<ProjectPraise>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_projectPraise where projectId = ? limit ? offset ?",
                new String[] {String.valueOf(projectId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            projectPraiseList.add(new ProjectPraise(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getString(cursor.getColumnIndex("praiseTime"))));
        }
        return projectPraiseList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取项目点赞总记录数
     */
    public long getCount(){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_projectPraise",null);// 获取项目点赞总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某个用户的项目点赞总记录数
     */
    public long getProjectCount(int userId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_projectPraise where userId = ?",
                new String[]{String.valueOf(userId)});// 获取某个用户的项目点赞总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某条项目的项目点赞总记录数
     */
    public long getUserCount(int projectId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_projectPraise where projectId = ?",
                new String[]{String.valueOf(projectId)});// 获取某条项目的项目点赞总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
