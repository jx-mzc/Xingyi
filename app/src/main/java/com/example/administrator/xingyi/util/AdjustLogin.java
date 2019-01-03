package com.example.administrator.xingyi.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.administrator.xingyi.login.LoginActivity;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/19 0019
 * Author:  Infinity
 */
public class AdjustLogin {

    public static Boolean isLogin(final Context context){
        SharedPreferencesUtils sp;
        Boolean isLogin;
        sp = new SharedPreferencesUtils(context);
        isLogin = (Boolean) sp.getParam("logining",false);
        if (isLogin){
            return true;
        }
        else {
            AlertDialog alertDialog;
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(context);
            alertDialog = builder.setTitle("你还未登录")
                    .setMessage("请登录或者注册")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton("去登录或注册", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        }
                    }).create();
            alertDialog.show();
            return false;
        }
    }
}
