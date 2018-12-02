package com.example.administrator.xingyi.register;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.User;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/01
 * Author:  Infinity
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private TitleBar titleBar;
    private EditText userName;
    private ImageView deleteUserName;
    private EditText pwd;
    private ImageView deletePwd;
    private EditText rePwd;
    private ImageView deleteRePwd;
    private EditText tel;
    private ImageView deleteTel;
    private EditText address;
    private ImageView deleteAddress;
    private Button register;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityCollector.addActivity(this);
        ininView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private void ininView() {
        titleBar = findViewById(R.id.title_register);
        userName = findViewById(R.id.et_register_account);
        deleteUserName = findViewById(R.id.iv_delete_register_username);
        pwd = findViewById(R.id.et_register_password);
        deletePwd = findViewById(R.id.iv_delete_register_pwd);
        rePwd = findViewById(R.id.et_register_repassword);
        deleteRePwd = findViewById(R.id.iv_delete_register_repwd);
        tel = findViewById(R.id.et_register_tel);
        deleteTel = findViewById(R.id.iv_delete_register_tel);
        address = findViewById(R.id.et_register_address);
        deleteAddress = findViewById(R.id.iv_delete_register_address);
        register = findViewById(R.id.btn_register);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //设置监听
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
        deleteUserName.setOnClickListener(this);
        deletePwd.setOnClickListener(this);
        deleteRePwd.setOnClickListener(this);
        deleteTel.setOnClickListener(this);
        deleteAddress.setOnClickListener(this);
        register.setOnClickListener(this);
        deleteUserNameListener();
        deletepwdListener();
        deleteRePwdListener();
        deleteTelListener();
        deleteAddressListener();
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/1 0001
     * @Description:  设置用户名编辑框监听
     */
    private void deleteUserNameListener() {
        userName.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length() > 0){
                    deleteUserName.setVisibility(View.VISIBLE);
                }else {
                    deleteUserName.setVisibility(View.GONE);
                }
            }
        });
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/1 0001
     * @Description:  设置密码编辑框监听
     */
    private void deletepwdListener(){
        pwd.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length() > 0){
                    deletePwd.setVisibility(View.VISIBLE);
                }else {
                    deletePwd.setVisibility(View.GONE);
                }
            }
        });
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/12/1 0001
     * @Description:  设置确认密码编辑框监听
     */
    private void deleteRePwdListener() {
        rePwd.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length() > 0){
                    deleteRePwd.setVisibility(View.VISIBLE);
                }else {
                    deleteRePwd.setVisibility(View.GONE);
                }
            }
        });
    }
    private void deleteTelListener(){
        tel.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length() > 0){
                    deleteTel.setVisibility(View.VISIBLE);
                }else {
                    deleteTel.setVisibility(View.GONE);
                }
            }
        });
    }
    private void deleteAddressListener() {
        address.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length() > 0){
                    deleteAddress.setVisibility(View.VISIBLE);
                }else {
                    deleteAddress.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_delete_register_username:
                userName.setText("");
                break;
            case R.id.iv_delete_register_pwd:
                pwd.setText("");
                break;
            case R.id.iv_delete_register_repwd:
                rePwd.setText("");
                break;
            case R.id.iv_delete_register_tel:
                tel.setText("");
                break;
            case R.id.iv_delete_register_address:
                address.setText("");
                break;
            case R.id.btn_register:
                if (userName.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,"请输入用户名！",Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                }else if (pwd.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,"请输入密码！",Toast.LENGTH_SHORT).show();
                    pwd.requestFocus();
                }else if (rePwd.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,"请输入确认密码！",Toast.LENGTH_SHORT).show();
                    rePwd.requestFocus();
                }else if (!pwd.getText().toString().equals(rePwd.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"两次密码不同，请重新输入！",Toast.LENGTH_SHORT).show();
                    pwd.setText("");
                    rePwd.setText("");
                    pwd.requestFocus();
                }else {
                    UserDAO userDAO = new UserDAO(this);
                    if(userDAO.query(userName.getText().toString())){
                        Toast.makeText(RegisterActivity.this,"该用户名已注册！",Toast.LENGTH_SHORT).show();
                        userName.setText("");
                        userName.requestFocus();
                    }else {
                        User user;
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                        String date = df.format(new Date());
                        int telNumber = 5201314;
                        String addressText = "未知";
                        if (!tel.getText().toString().equals("")){
                            telNumber = Integer.parseInt(tel.getText().toString());
                        }
                        if (!address.getText().toString().equals("")){
                            addressText = address.getText().toString();
                        }
                        user = new User(0,userName.getText().toString(),pwd.getText().toString(),telNumber,
                                addressText,date,0,0,0,0);
                        userDAO.add(user);
                        editor = pref.edit();
                        editor.putBoolean("auto_login",true);
                        editor.putBoolean("remember_password",true);
                        editor.putInt("user_id",userDAO.query(userName.getText().toString(),pwd.getText().toString()).get_id());
                        editor.putString("user_name",userName.getText().toString());
                        editor.putString("password",pwd.getText().toString());
                        editor.putBoolean("logining",true);
                        editor.apply();
                        ActivityCollector.activities.get(0).recreate();
                        ActivityCollector.activities.get(1).finish();
                        finish();
                    }
                }
        }
    }

}
