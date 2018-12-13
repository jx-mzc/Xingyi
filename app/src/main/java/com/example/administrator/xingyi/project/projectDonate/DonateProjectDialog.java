package com.example.administrator.xingyi.project.projectDonate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.customView.DelEditText;
import com.example.administrator.xingyi.dao.DonateDAO;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.Donate;
import com.example.administrator.xingyi.model.User;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import Utils.ScreenUtils;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/9 0009
 * Author:  Infinity
 */
public class DonateProjectDialog {
    private int userId;
    private int projectId;
    private View view;
    private Context context;
    private User user;
    private AlertDialog dialog;
    private TextView tvStars;
    private RadioGroup rgDonate;
    private RelativeLayout rlDonateCustom;
    private ImageView ivDonateCustom;
    private EditText etDonateCustom;
    private TextView tvDonateCustom;
    private ImageView ivDonateExit;
    private Button btConfirmDonate;
    private int donateStars;

    public DonateProjectDialog(View view, Context context,int userId,int projectId) {
        this.view = view;
        this.context = context;
        this.userId = userId;
        this.projectId = projectId;
    }

    public void showDialog(){
        dialog = new AlertDialog.Builder(context).setView(view).create();
        tvStars = view.findViewById(R.id.tv_project_stars);
        rgDonate = view.findViewById(R.id.rg_donate);
        rlDonateCustom = view.findViewById(R.id.rl_donate_custom);
        ivDonateCustom = view.findViewById(R.id.iv_donate_custom);
        etDonateCustom = view.findViewById(R.id.de_donate_custom);
        tvDonateCustom = view.findViewById(R.id.tv_donate_custom);
        ivDonateExit = view.findViewById(R.id.iv_project_donate_exit);
        btConfirmDonate = view.findViewById(R.id.bt_confirm_donate);
        //user = new UserDAO(context).query(userId);
        //获取用户可捐赠星星数
       // tvStars.setText(String.valueOf(user.getDonableStars()));
        //设置监听
        rgDonate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = view.findViewById(i);
                if (ivDonateCustom.isSelected()){
                    ivDonateCustom.setSelected(false);
                    tvDonateCustom.setSelected(false);
                    etDonateCustom.setEnabled(false);
                }
                btConfirmDonate.setEnabled(true);
                btConfirmDonate.setAlpha(1);
            }
        });
        rlDonateCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0;i<rgDonate.getChildCount();i=i+2){
                    RadioButton radioButton = (RadioButton) rgDonate.getChildAt(i);
                       if (radioButton.isChecked()){
                           rgDonate.clearCheck();
                       }
                }
                ivDonateCustom.setSelected(true);
                tvDonateCustom.setSelected(true);
                etDonateCustom.setEnabled(true);
                etDonateCustom.setFocusable(true);
                etDonateCustom.setFocusableInTouchMode(true);
                etDonateCustom.requestFocus();
                InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                //软键盘弹出
                inputManager.showSoftInput(etDonateCustom, 0);
                btConfirmDonate.setEnabled(false);
                btConfirmDonate.setAlpha(0.5f);
                etDonateCustom.addTextChangedListener(new TextWatcher() {

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
                        if(temp.length() > 0){
                            if (Integer.parseInt(temp.toString())!=0){
                                btConfirmDonate.setEnabled(true);
                                btConfirmDonate.setAlpha(1);
                            }
                        }else {
                            btConfirmDonate.setEnabled(false);
                            btConfirmDonate.setAlpha(0.5f);
                        }
                    }
                });
            }
        });
        ivDonateExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btConfirmDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ivDonateCustom.isSelected()){
                    donateStars = Integer.parseInt(etDonateCustom.getText().toString());
                }else {
                    for (int i = 0;i<rgDonate.getChildCount();i=i+2){
                        RadioButton radioButton = (RadioButton) rgDonate.getChildAt(i);
                        if (radioButton.isChecked()){
                            donateStars = Integer.parseInt(radioButton.getText().toString().substring(0,radioButton.getText().toString().indexOf("颗")));
                        }
                    }
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String date = df.format(new Date());
                Donate donate = new Donate(0,userId,projectId,donateStars,date);
                DonateDAO donateDAO = new DonateDAO(context);
                if (donateDAO.query(userId,projectId)!=null){//如果捐赠过该项目，则更新捐赠记录
                    donate.setDonateStarsNum(donateDAO.query(userId,projectId).getDonateStarsNum()+donateStars);
                    donate.set_id(donateDAO.query(userId,projectId).get_id());
                    donateDAO.update(donate);
                }else {
                    donateDAO.add(donate);//添加捐赠记录
                }
                dialog.dismiss();
                AlertDialog alertDialog;
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context);
                alertDialog = builder.setTitle("感谢您的捐赠")
                        .setMessage("您捐赠了"+donateStars+"颗星星，谢谢您为本次公益项目贡献的一份力！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ScreenUtils.getScreenWidth(context),ScreenUtils.getScreenHeight(context));
    }
}
