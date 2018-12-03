package com.example.administrator.xingyi.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ting on 2018/11/29.
 */

public class SharedPreferencesUtils {
    public Context myContext;
    public String spName;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    public SharedPreferencesUtils(Context context, String name){
        myContext = context;
        spName = name;
        sp = myContext.getSharedPreferences(spName, 0);
        editor = sp.edit();
    }

    public void putBoolean(String name, boolean initValue){
        editor.putBoolean(name, initValue);
    }

    public boolean getBoolean(String name, boolean flag){
        return sp.getBoolean(name, flag);
    }

    public String getString(String name){
        return sp.getString(name, name);
    }

    public int getInt(String name){
        return sp.getInt(name, 0);
    }

    public float getFloat(String name){
        return sp.getFloat(name, 0);
    }

    public long getLong(String name){
        return sp.getLong(name, 0);
    }
}
