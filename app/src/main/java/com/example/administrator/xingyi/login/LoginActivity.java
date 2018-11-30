package com.example.administrator.xingyi.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xingyi.MainActivity;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.User;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/28
 * Author:  ting
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText accountEdit;
    private EditText passwordEdit;
    private ImageView hideOrShowImage;
    private ImageView deleteAccount;
    private ImageView deletePwd;
    private CheckBox rememberPassword;
    private CheckBox autoLogin;
    private Button loginBut;
    private TextView register;
    private TextView forgetPwd;
    private Boolean isRemember;
    private Boolean isAutoLogin;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        accountEdit = findViewById(R.id.et_account);
        passwordEdit = findViewById(R.id.et_password);
        hideOrShowImage = findViewById(R.id.iv_see_password);
        deleteAccount = findViewById(R.id.iv_delete_username);
        deletePwd = findViewById(R.id.iv_delete_pwd);
        rememberPassword = findViewById(R.id.checkBox_password);
        autoLogin = findViewById(R.id.checkBox_login);
        loginBut = findViewById(R.id.btn_login);
        register = findViewById(R.id.tv_register);
        forgetPwd = findViewById(R.id.tv_forget_pwd);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //实现记住密码功能
        isRemember = pref.getBoolean("remember_password",false);
        //实现自动登录功能
        isAutoLogin = pref.getBoolean("auto_login",false);
        if (isAutoLogin){
            autoLogin.setChecked(true);
            //创建一个意图
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else if (isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassword.setChecked(true);
            accountEdit.setSelection(accountEdit.getText().length());
        }

        //设置点击事件
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                        rememberPassword.setChecked(true);
                }
            }
        });
        rememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b){
                    autoLogin.setChecked(false);
                }
            }
        });
        hideOrShowImage.setOnClickListener(this);
        loginBut.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
        deleteAccount.setOnClickListener(this);
        deletePwd.setOnClickListener(this);
        accountListener();
        pwdListener();
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/30 0030
     * @Description:  账户文本框监听
     */
    private void accountListener() {
        accountEdit.addTextChangedListener(new TextWatcher() {
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
                    deleteAccount.setVisibility(View.VISIBLE);
                }else {
                    deleteAccount.setVisibility(View.GONE);
                }
            }
        });
    }
    /**
     * @Author:  Infinity
     * @Date:  2018/11/30 0030
     * @Description:  密码文本框监听
     */
    private void pwdListener() {
        passwordEdit.addTextChangedListener(new TextWatcher() {
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_see_password:
                if (hideOrShowImage.isSelected()) {
                    hideOrShowImage.setSelected(false);
                    //密码不可见
                    passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //光标移到行尾
                    passwordEdit.setSelection(passwordEdit.getText().length());
                } else {
                    hideOrShowImage.setSelected(true);
                    //密码可见
                    passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //光标移到行尾
                    passwordEdit.setSelection(passwordEdit.getText().length());
                }
                break;

            case R.id.iv_delete_username:
                accountEdit.setText("");
                break;

            case R.id.iv_delete_pwd:
                passwordEdit.setText("");
                break;

            case R.id.btn_login:
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                UserDAO userDAO = new UserDAO(this);
                if (userDAO.query(account)){
                    User user = userDAO.query(account, password);
                    if (user != null){
                    editor = pref.edit();
                    if (rememberPassword.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                        if (autoLogin.isChecked()){
                            editor.putBoolean("auto_login",true);
                        }else {
                            editor.putBoolean("auto_login",false);
                        }
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"密码错误！",Toast.LENGTH_SHORT).show();
                        passwordEdit.setText("");
                        passwordEdit.requestFocus();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"该账户不存在！",Toast.LENGTH_SHORT).show();
                    accountEdit.setText("");
                    accountEdit.requestFocus();
                }
                break;

            case R.id.tv_register:
                String account2 = accountEdit.getText().toString();
                String password2 = passwordEdit.getText().toString();
                UserDAO userDAO2 = new UserDAO(this);
                User user = new User();
                user.setName(account2);
                user.setPwd(password2);
                user.setAddress("鸡");
                user.setDonableStars(100);
                user.setDonatedStars(100);
                user.setExchangeableStars(100);
                user.setExchangedStars(100);
                user.setRegistrationDate("鸡");
                userDAO2.add(user);
                Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
