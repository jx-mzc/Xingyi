package com.example.administrator.xingyi.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
    private CheckBox rememberPassword;
    private CheckBox autoLogin;
    private Button loginBut;
    private Button register;

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
        rememberPassword = findViewById(R.id.checkBox_password);
        autoLogin = findViewById(R.id.checkBox_login);
        loginBut = findViewById(R.id.btn_login);
        register = findViewById(R.id.register);
        //设置点击事件
        hideOrShowImage.setOnClickListener(this);
        loginBut.setOnClickListener(this);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_see_password:
                if (passwordEdit.isSelected()) {
                    passwordEdit.setSelected(false);
                    //密码不可见
                    passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                } else {
                    passwordEdit.setSelected(true);
                    //密码可见
                    passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;

            case R.id.btn_login:
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                UserDAO userDAO = new UserDAO(this);
                User user1 = userDAO.query(account, password);
                if(null != user1){
                    Toast.makeText(this,"输入的账号为："+account+" 输入的密码为："+password, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.register:
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
