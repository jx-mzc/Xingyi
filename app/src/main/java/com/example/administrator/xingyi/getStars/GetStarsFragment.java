package com.example.administrator.xingyi.getStars;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.StepNumDAO;
import com.example.administrator.xingyi.getStars.clocks.ClocksActivity;
import com.example.administrator.xingyi.getStars.stepCount.StepCountActivity;
import com.example.administrator.xingyi.getStars.stepCount.UpdateUiCallBack;
import com.example.administrator.xingyi.model.StepNum;
import com.example.administrator.xingyi.util.AdjustLogin;
import com.example.administrator.xingyi.util.SharedPreferencesUtils;
import com.hjq.bar.TitleBar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/27 0027
 * Author:  Infinity
 */
public class GetStarsFragment extends Fragment {

    private RelativeLayout rlStepCount;
    private RelativeLayout rlClocks;
    private TextView tvStepNum;
    private SharedPreferencesUtils sp;
    private StepNum stepNum;
    private StepNumDAO stepNumDAO;
    private int userId;
    private String date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_getstars,null);
        rlStepCount = view.findViewById(R.id.rv_getstars_step);
        tvStepNum = view.findViewById(R.id.tv_getstars_step);
        rlClocks = view.findViewById(R.id.rv_getstars_clocks);

        sp = new SharedPreferencesUtils(getContext());
        userId = (int) sp.getParam("user_id",0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        date = df.format(new Date());
        if ((Boolean)sp.getParam("logining",false)){
            stepNumDAO = new StepNumDAO(getContext());
            stepNum = stepNumDAO.query(userId,date);
            tvStepNum.setText("今日步数："+String.valueOf(stepNum.getStepNums())+"步");
        }else {
            tvStepNum.setText("今日步数：0步");
        }

        rlStepCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AdjustLogin.isLogin((getContext()))){
                    StepCountActivity.actionStars(getContext(),userId,date);
                }
            }
        });
        //监听回调
        StepCountActivity.registerCallback(new UpdateUiCallBack(){

            @Override
            public void updateUi(int stepCount) {
                tvStepNum.setText("今日步数："+String.valueOf(stepCount)+"步");
            }
        });

        rlClocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AdjustLogin.isLogin((getContext()))) {
                    Intent intent = new Intent(getContext(), ClocksActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
