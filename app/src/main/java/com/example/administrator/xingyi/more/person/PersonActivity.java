package com.example.administrator.xingyi.more.person;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.User;
import com.example.administrator.xingyi.more.RoundImageView;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener{

    private TitleBar titleBar;
    private RoundImageView touxiang;
    private TextView tvUserName;
    private TextView tvTel;
    private TextView tvAddress;
    private TextView registrationDate;
    private RelativeLayout rlQrCode;
    private RelativeLayout rlLogOut;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ActivityCollector.addActivity(this);
        initView();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_person);
        touxiang = findViewById(R.id.person_round_touxiang);
        tvUserName = findViewById(R.id.tv_person_username);
        tvTel = findViewById(R.id.tv_person_tel);
        tvAddress = findViewById(R.id.tv_person_address);
        registrationDate = findViewById(R.id.tv_person_registrationDate);
        rlQrCode = findViewById(R.id.rl_person_qrcode);
        rlLogOut = findViewById(R.id.rl_person_logout);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //获取用户数据
        int userId = pref.getInt("user_id",0);
        UserDAO userDAO = new UserDAO(this);
        User user = userDAO.query(userId);
        //将用户数据写入页面
        tvUserName.setText(user.getName());
        tvTel.setText(String.valueOf(user.getTel()));
        tvAddress.setText(user.getAddress());
        registrationDate.setText(user.getRegistrationDate());
        //设置监听事件
        touxiang.setOnClickListener(this);
        rlQrCode.setOnClickListener(this);
        rlLogOut.setOnClickListener(this);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.person_round_touxiang:
                break;
            case R.id.rl_person_qrcode:
                break;
            case R.id.rl_person_logout:
                break;
            default:
                break;
        }
    }
}
