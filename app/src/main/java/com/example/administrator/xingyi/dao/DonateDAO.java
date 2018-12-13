package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.administrator.xingyi.model.Donate;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/24 0024
 * Author:  Infinity
 */
public class DonateDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  定义构造函数
     */
    public DonateDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  添加捐赠信息
     */
    public void add(Donate donate){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("userId",donate.getUserId());
        values.put("projectId",donate.getProjectId());
        values.put("donateStarsNum",donate.getDonateStarsNum());
        values.put("donateTime",donate.getDonateTime());
        db.insert("tb_donate",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  删除捐赠信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_donate","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  修改捐赠信息
     */
    public void update(Donate donate) {
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("userId",donate.getUserId());
        values.put("projectId",donate.getProjectId());
        values.put("donateStarsNum",donate.getDonateStarsNum());
        values.put("donateTime",donate.getDonateTime());
        db.update("tb_donate",values,"_id = ?",new String[]{String.valueOf(donate.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  查询单条捐赠信息
     */
    public Donate query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_donate",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Donate类中返回
            return new Donate(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("donateStarsNum")),
                    cursor.getString(cursor.getColumnIndex("donateTime")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/11 0011
     * @Description:  通过用户ID和项目ID查询捐赠记录
     */
    public Donate query(int userId,int projectId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_donate",null,"userId = ? and projectId = ?",
                new String[]{String.valueOf(userId),String.valueOf(projectId)}, null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Donate类中返回
            return new Donate(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("donateStarsNum")),
                    cursor.getString(cursor.getColumnIndex("donateTime")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  查询所有捐赠信息
     */
    public List<Donate> getScrollData(long start, long count){
        List<Donate> donateList = new ArrayList<Donate>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_donate limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            donateList.add(new Donate(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("donateStarsNum")),
                    cursor.getString(cursor.getColumnIndex("donateTime"))));
        }
        return donateList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  查询某个用户的捐赠信息
     */
    public List<Donate> getProjectScrollData(long start, long count,int userId){
        List<Donate> donateList = new ArrayList<Donate>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_donate where userId = ? limit ? offset ?",
                new String[] {String.valueOf(userId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            donateList.add(new Donate(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("donateStarsNum")),
                    cursor.getString(cursor.getColumnIndex("donateTime"))));
        }
        return donateList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/4 0004
     * @Description:  获取某个项目已捐星星数
     */
    public long getStarsSum(int projectId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select sum(donateStarsNum) from tb_donate where projectId = ?",new String[]{String.valueOf(projectId)});// 获取捐赠总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  查询某个项目的捐赠信息，按照捐赠星星数降序排序
     */
    public List<Donate> getUserScrollData(long start, long count,int projectId){
        List<Donate> donateList = new ArrayList<Donate>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_donate where userId = ? order by donateStarsNum desc limit ? offset ?",
                new String[] {String.valueOf(projectId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            donateList.add(new Donate(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("projectId")),
                    cursor.getInt(cursor.getColumnIndex("userId")),
                    cursor.getInt(cursor.getColumnIndex("donateStarsNum")),
                    cursor.getString(cursor.getColumnIndex("donateTime"))));
        }
        return donateList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  获取捐赠总记录数
     */
    public long getCount(){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_donate",null);// 获取捐赠总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  获取某个用户的总捐赠项目记录数
     */
    public long getProjectCount(int userId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_donate where userId = ?",
                new String[]{String.valueOf(userId)});// 获取某个用户的总捐赠项目记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/24 0024
     * @Description:  获取某个项目的总捐赠用户记录数
     */
    public long getUserCount(int projectId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_donate where projextId = ?",
                new String[]{String.valueOf(projectId)});// 获取某个用户的总捐赠项目记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
