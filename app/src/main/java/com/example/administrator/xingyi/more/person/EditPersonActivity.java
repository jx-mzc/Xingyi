package com.example.administrator.xingyi.more.person;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.customView.DelEditText;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.User;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.Objects;

public class EditPersonActivity extends AppCompatActivity {

    private TitleBar titleBar;
    private DelEditText etTel;
    private DelEditText etAddress;
    private SharedPreferences pref;
    private AlertDialog alert ;
    private AlertDialog.Builder builder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        initView();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_edit_person);
        etTel = findViewById(R.id.et_edit_tel);
        etAddress = findViewById(R.id.et_edit_address);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = pref.getInt("user_id",0);
        final UserDAO userDAO = new UserDAO(this);
        final User user = userDAO.query(userId);
        etTel.requestFocus();
        etTel.setSelection(Objects.requireNonNull(etTel.getText()).length());
        etTel.setText(String.valueOf(user.getTel()));
        etAddress.setText(user.getAddress());
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
                builder = new AlertDialog.Builder(EditPersonActivity.this);
                alert = builder.setTitle("你确定修改？")
                        .setMessage("请确认修改内容")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int tel = 5201314;
                                String address = "未知";
                                if (!etTel.getText().toString().equals("")){
                                    tel = Integer.parseInt(etTel.getText().toString());
                                }
                                if (!etAddress.getText().toString().equals("")){
                                    address = etAddress.getText().toString();
                                }
                                user.setTel(tel);
                                user.setAddress(address);
                                userDAO.update(user);
                                Toast.makeText(EditPersonActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
            }
        });
    }
}
