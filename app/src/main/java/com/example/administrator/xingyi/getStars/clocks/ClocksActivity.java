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
    private TextView tvYear, tvMonth, tvDay;
    int mYear, mMonth, mDay;
    int mDays, week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocks);
        ActivityCollector.addActivity(this);
        init();
        initDate();
    }

    private void init() {
        tvYear = (TextView) this.findViewById(R.id.clock_tv_year);
        tvMonth = (TextView) this.findViewById(R.id.clock_tv_month);
        tvDay = (TextView) this.findViewById(R.id.clock_tv_day);
    }

    private void initDate() {


        titleBar = findViewById(R.id.title_clocks);

        Calendar calendar = Calendar.getInstance(Locale.CHINA);

        int day = calendar.getActualMaximum(Calendar.DATE); // 获取当前月的天数

        mYear = calendar.get(Calendar.YEAR); // 获取当前年份
        tvYear.setText(mYear+"");
        mMonth = calendar.get(Calendar.MONTH);// 获取当前月份以（0开头）
        tvMonth.setText(mMonth+1+"");
        mDay = calendar.get(Calendar.DAY_OF_MONTH);// 获取当前天以（0开头）
        tvDay.setText(mDay+"");
        boolean isLeapYear = isLeapYear(mYear);//判断是否为闰年
        mDays = getDaysOfMonth(isLeapYear, mMonth + 1);//这个月的天数
        week = getWeekdayOfMonth(mYear, mMonth);//这个月的一号是周几

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

        for (int i = 1; i <= mDays + week; i++) {

            CheckBean checkBean = new CheckBean();

            if (i <= mDay + 1 && i >= week) {

                checkBean.day = i - week;

                checkBean.check_status = CheckBean.CHECK_WAIT;//未签到

            } else if (i < week) {
                checkBean.day = 0;
                checkBean.check_status = CheckBean.CHECK_WAIT;//未签到
            } else if (i > mDay + 1) {

                checkBean.day = i - week;

                checkBean.check_status = CheckBean.CHECK_NO;//没有签到

            } else {

                checkBean.day = i - week;

                checkBean.check_status = CheckBean.CHECKED;//已经签到

            }

            checkBeanList.add(checkBean);

        }

        mAdapter = new MyAdapter(ClocksActivity.this);
        mAdapter.setListDate(checkBeanList);
        mGridview = (GridView) findViewById(R.id.main_gridview);
        mGridview.setAdapter(mAdapter);

    }

    /**
     * 判断是否是闰年
     */
    public boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 得到某月多少天
     * 1357810腊三十一天永不差，469冬三十日平年二月28，闰年再把一天加。
     */
    public int getDaysOfMonth(boolean isLeapYear, int month) {
        int days = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                if (isLeapYear) {
                    days = 29;
                } else {
                    days = 28;
                }
        }
        return days;
    }


    /**
     * 得到某年某月一号是星期几  （0-6 日-六）
     */
    public int getWeekdayOfMonth(int mYear, int mMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, 1);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
