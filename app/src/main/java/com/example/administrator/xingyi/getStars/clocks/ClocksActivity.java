package com.example.administrator.xingyi.getStars.clocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ClocksActivity extends AppCompatActivity {

    private TitleBar titleBar;
    private List<CheckBean> checkBeanList;
    private MyAdapter mAdapter;
    private GridView mGridview;
    private TextView monthTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocks);
        ActivityCollector.addActivity(this);
        initDate();
    }

    private void initDate() {


        titleBar = findViewById(R.id.title_clocks);

        Calendar calendar = Calendar.getInstance(Locale.CHINA);

        int day = calendar.getActualMaximum(Calendar.DATE); // 获取当前月的天数


        checkBeanList = new ArrayList<CheckBean>();

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

        for (int i = 0; i < day + 1; i++) {

            CheckBean checkBean = new CheckBean();

            if ((int) (Math.random() * 20 % 4) == 3) {

                checkBean.day = i;

                checkBean.check_status = CheckBean.CHECKED;

            } else if ((int) (Math.random() * 20 % 4) == 2) {

                checkBean.day = i;

                checkBean.check_status = CheckBean.CHECK_NO;

            } else {

                checkBean.day = i;

                checkBean.check_status = CheckBean.CHECK_WAIT;

            }

            checkBeanList.add(checkBean);

        }

        mAdapter = new MyAdapter(ClocksActivity.this);
        mAdapter.setListDate(checkBeanList);
        mGridview = (GridView) findViewById(R.id.main_gridview);
        mGridview.setAdapter(mAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
