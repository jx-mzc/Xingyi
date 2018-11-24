package com.example.administrator.xingyi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/23 0023
 * Author:  Infinity
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;// 定义数据库版本号
    private static final String DBNAME = "XingYiDB.db";// 定义数据库名

    public MyDatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
