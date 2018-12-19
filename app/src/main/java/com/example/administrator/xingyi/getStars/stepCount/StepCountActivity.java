package com.example.administrator.xingyi.getStars.stepCount;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.customView.MyLineChart;
import com.example.administrator.xingyi.customView.StepArcView;
import com.example.administrator.xingyi.dao.StepNumDAO;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.getStars.stepCount.lineChart.InitLineChart;
import com.example.administrator.xingyi.model.StepNum;
import com.example.administrator.xingyi.model.User;
import com.example.administrator.xingyi.util.SharedPreferencesUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StepCountActivity extends AppCompatActivity {

    private TitleBar titleBar;
    private StepArcView stepArcView;
    private Button btExchangeStars;
    private MyLineChart mLineChart;
    private SharedPreferencesUtils sp;
    private StepNum stepNum;
    private StepNumDAO stepNumDAO;
    private int userId;
    private String date;
    private boolean isBind = false;
    /**
     * UI监听器对象
     */
    private static UpdateUiCallBack mCallback;


    public static void actionStars(Context context,int userId,String date){
        Intent intent = new Intent(context, StepCountActivity.class);
        intent.putExtra("user_id",userId);
        intent.putExtra("date",date);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);
        ActivityCollector.addActivity(this);
        userId = getIntent().getIntExtra("user_id",0);
        date = getIntent().getStringExtra("date");
        initView();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_getstars_step_count);
        stepArcView = findViewById(R.id.sav);
        btExchangeStars = findViewById(R.id.bt_exchange_stars_steps);
        mLineChart = findViewById(R.id.chart_day);
        stepNumDAO = new StepNumDAO(this);
        stepNum = stepNumDAO.query(userId,date);
        //获取今日步数
        stepArcView.setCurrentCount(2000,stepNum.getStepNums());
        //开启计步服务
        setupService();
        if (stepNum.getStepNums() >= 2000){
            btExchangeStars.setAlpha(1);
            btExchangeStars.setEnabled(true);
        }
        //设置今日步数详细折线图
        new InitLineChart(mLineChart,userId,date,stepNum.get_id(),this).creatLineChart();
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

        //设置兑换按钮监听
        btExchangeStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stars;
                stars = (stepNum.getStepNums()/2000)*200;
                UserDAO userDAO = new UserDAO(StepCountActivity.this);
                User user = userDAO.query(userId);
                user.setDonableStars(user.getDonableStars()+stars);
                userDAO.update(user);
            }
        });
    }

    /**
     * 开启计步服务
     */
    private void setupService() {
        Intent intent = new Intent(this, StepService.class);
        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);
    }
    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            //设置初始化数据
            stepArcView.setCurrentCount(2000, stepService.getStepCount());

            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    stepArcView.setCurrentCount(2000, stepCount);
                    new InitLineChart(mLineChart,userId,date,stepNum.get_id(),StepCountActivity.this).creatLineChart();
                    mCallback.updateUi(stepCount);
                }
            } );
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    /**
     * 注册UI更新监听
     *
     * @param paramICallback
     */

    public static void registerCallback(UpdateUiCallBack paramICallback) {
        mCallback = paramICallback;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBind) {
            this.unbindService(conn);
        }
        ActivityCollector.removeActivity(this);
    }
}
