package com.example.administrator.xingyi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.xingyi.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/25 0025
 * Author:  Infinity
 */
public class ProjectDAO {
    private MyDatabaseHelper myDatabaseHelper;//创建MyDatabaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    private ContentValues values;
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  定义构造函数
     */
    public ProjectDAO(Context context){
        myDatabaseHelper = new MyDatabaseHelper(context);//初始化MyDatabaseHelper对象
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  添加项目信息
     */
    public void add(Project project){
        db = myDatabaseHelper.getWritableDatabase();
        values = new ContentValues();
        //开始组装数据
        values.put("adminId",project.getAdminId());
        values.put("projectImgRes",project.getProjectImgRes());
        values.put("projectName",project.getProjectName());
        values.put("projectIntroduction",project.getProjectIntroduction());
        values.put("projectUse",project.getProjectUse());
        values.put("companyId",project.getCompanyId());
        values.put("projectSponser",project.getProjectSponser());
        values.put("organizationId",project.getOrganizationId());
        values.put("projectOriginator",project.getProjectOriginator());
        values.put("projectNeed",project.getProjectNeed());
        values.put("projectHave",project.getProjectHave());
        values.put("projectMessageNum",project.getProjectMessageNum());
        values.put("projectPraiseNum",project.getProjectPraiseNum());
        db.insert("tb_project",null,values);
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  删除项目信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
            for (int _id:ids) {
                db.delete("tb_project","_id = ?",new String[]{String.valueOf(_id)});
            }
        }
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  更新项目信息
     */
    public void update(Project project){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        values = new ContentValues();
        //开始组装数据
        values.put("adminId",project.getAdminId());
        values.put("projectImgRes",project.getProjectImgRes());
        values.put("projectName",project.getProjectName());
        values.put("projectIntroduction",project.getProjectIntroduction());
        values.put("projectUse",project.getProjectUse());
        values.put("companyId",project.getCompanyId());
        values.put("projectSponser",project.getProjectSponser());
        values.put("organizationId",project.getOrganizationId());
        values.put("projectOriginator",project.getProjectOriginator());
        values.put("projectNeed",project.getProjectNeed());
        values.put("projectHave",project.getProjectHave());
        values.put("projectMessageNum",project.getProjectMessageNum());
        values.put("projectPraiseNum",project.getProjectPraiseNum());
        db.update("tb_project",values,"_id = ?",new String[]{String.valueOf(project.get_id())});
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询单条项目信息
     */
    public Project query(int _id){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.query("tb_project",null,"_id = ?",new String[]{String.valueOf(_id)},
                null,null,null);
        if (cursor.moveToNext()){
            //遍历Cursor对象，并将数据存储到Project类中返回
            return new Project(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("adminId")),
                    cursor.getInt(cursor.getColumnIndex("projectImgRes")),
                    cursor.getString(cursor.getColumnIndex("projectName")),
                    cursor.getString(cursor.getColumnIndex("projectIntroduction")),
                    cursor.getString(cursor.getColumnIndex("projectUse")),
                    cursor.getInt(cursor.getColumnIndex("companyId")),
                    cursor.getString(cursor.getColumnIndex("projectSponser")),
                    cursor.getInt(cursor.getColumnIndex("organizationId")),
                    cursor.getString(cursor.getColumnIndex("projectOriginator")),
                    cursor.getInt(cursor.getColumnIndex("projectNeed")),
                    cursor.getInt(cursor.getColumnIndex("projectHave")),
                    cursor.getInt(cursor.getColumnIndex("projectMessageNum")),
                    cursor.getInt(cursor.getColumnIndex("projectPraiseNum")));
        }
        return null;// 如果没有信息，则返回null
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询所有项目信息
     */
    public List<Project> getScrollData(int start, int count){
        List<Project> projectList = new ArrayList<Project>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_project limit ? offset ?",
                new String[] {String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            projectList.add(new Project(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("adminId")),
                    cursor.getInt(cursor.getColumnIndex("projectImgRes")),
                    cursor.getString(cursor.getColumnIndex("projectName")),
                    cursor.getString(cursor.getColumnIndex("projectIntroduction")),
                    cursor.getString(cursor.getColumnIndex("projectUse")),
                    cursor.getInt(cursor.getColumnIndex("companyId")),
                    cursor.getString(cursor.getColumnIndex("projectSponser")),
                    cursor.getInt(cursor.getColumnIndex("organizationId")),
                    cursor.getString(cursor.getColumnIndex("projectOriginator")),
                    cursor.getInt(cursor.getColumnIndex("projectNeed")),
                    cursor.getInt(cursor.getColumnIndex("projectHave")),
                    cursor.getInt(cursor.getColumnIndex("projectMessageNum")),
                    cursor.getInt(cursor.getColumnIndex("projectPraiseNum"))));
        }
        return projectList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个管理员发布的所有项目信息
     */
    public List<Project> getAdminProjectScrollData(int start, int count,int adminId){
        List<Project> newsPraiseList = new ArrayList<Project>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_project where adminId = ? limit ? offset ?",
                new String[] {String.valueOf(adminId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            newsPraiseList.add(new Project(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("adminId")),
                    cursor.getInt(cursor.getColumnIndex("projectImgRes")),
                    cursor.getString(cursor.getColumnIndex("projectName")),
                    cursor.getString(cursor.getColumnIndex("projectIntroduction")),
                    cursor.getString(cursor.getColumnIndex("projectUse")),
                    cursor.getInt(cursor.getColumnIndex("companyId")),
                    cursor.getString(cursor.getColumnIndex("projectSponser")),
                    cursor.getInt(cursor.getColumnIndex("organizationId")),
                    cursor.getString(cursor.getColumnIndex("projectOriginator")),
                    cursor.getInt(cursor.getColumnIndex("projectNeed")),
                    cursor.getInt(cursor.getColumnIndex("projectHave")),
                    cursor.getInt(cursor.getColumnIndex("projectMessageNum")),
                    cursor.getInt(cursor.getColumnIndex("projectPraiseNum"))));
        }
        return newsPraiseList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个爱心企业资助的所有项目信息
     */
    public List<Project> getCompanyProjectScrollData(int start, int count,int companyId){
        List<Project> newsPraiseList = new ArrayList<Project>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_project where companyId = ? limit ? offset ?",
                new String[] {String.valueOf(companyId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            newsPraiseList.add(new Project(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("adminId")),
                    cursor.getInt(cursor.getColumnIndex("projectImgRes")),
                    cursor.getString(cursor.getColumnIndex("projectName")),
                    cursor.getString(cursor.getColumnIndex("projectIntroduction")),
                    cursor.getString(cursor.getColumnIndex("projectUse")),
                    cursor.getInt(cursor.getColumnIndex("companyId")),
                    cursor.getString(cursor.getColumnIndex("projectSponser")),
                    cursor.getInt(cursor.getColumnIndex("organizationId")),
                    cursor.getString(cursor.getColumnIndex("projectOriginator")),
                    cursor.getInt(cursor.getColumnIndex("projectNeed")),
                    cursor.getInt(cursor.getColumnIndex("projectHave")),
                    cursor.getInt(cursor.getColumnIndex("projectMessageNum")),
                    cursor.getInt(cursor.getColumnIndex("projectPraiseNum"))));
        }
        return newsPraiseList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  查询某个爱心组织发起的所有项目信息
     */
    public List<Project> getOrganizationProjectScrollData(int start, int count,int organizationId){
        List<Project> newsPraiseList = new ArrayList<Project>();// 创建集合对象
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_project where organizationId = ? limit ? offset ?",
                new String[] {String.valueOf(organizationId),String.valueOf(count),String.valueOf(start-1)});
        while (cursor.moveToNext()){
            //遍历Cursor对象，并将数据添加到集合中返回
            newsPraiseList.add(new Project(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getInt(cursor.getColumnIndex("adminId")),
                    cursor.getInt(cursor.getColumnIndex("projectImgRes")),
                    cursor.getString(cursor.getColumnIndex("projectName")),
                    cursor.getString(cursor.getColumnIndex("projectIntroduction")),
                    cursor.getString(cursor.getColumnIndex("projectUse")),
                    cursor.getInt(cursor.getColumnIndex("companyId")),
                    cursor.getString(cursor.getColumnIndex("projectSponser")),
                    cursor.getInt(cursor.getColumnIndex("organizationId")),
                    cursor.getString(cursor.getColumnIndex("projectOriginator")),
                    cursor.getInt(cursor.getColumnIndex("projectNeed")),
                    cursor.getInt(cursor.getColumnIndex("projectHave")),
                    cursor.getInt(cursor.getColumnIndex("projectMessageNum")),
                    cursor.getInt(cursor.getColumnIndex("projectPraiseNum"))));
        }
        return newsPraiseList;// 返回集合
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取项目总记录数
     */
    public long getCount(){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_project",null);// 获取项目总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某个管理员发布的项目总记录数
     */
    public long getAdminProjectCount(int adminId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_project where adminId = ?",
                new String[]{String.valueOf(adminId)});// 获取某个管理员发布的项目总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某个爱心企业资助的项目总记录数
     */
    public long getCompanyProjectCount(int companyId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_project where companyId = ?",
                new String[]{String.valueOf(companyId)});// 获取某个爱心企业资助的项目总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/25 0025
     * @Description:  获取某个爱心组织发起的项目总记录数
     */
    public long getOrganizationProjectCount(int organizationId){
        db = myDatabaseHelper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_project where organizationId = ?",
                new String[]{String.valueOf(organizationId)});// 获取某个爱心组织发起的项目总记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
}
