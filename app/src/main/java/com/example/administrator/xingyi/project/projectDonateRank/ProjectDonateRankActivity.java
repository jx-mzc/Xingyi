package com.example.administrator.xingyi.project.projectDonateRank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.DonateDAO;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.Donate;
import com.example.administrator.xingyi.model.User;
import com.example.administrator.xingyi.more.RoundImageView;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.List;

public class ProjectDonateRankActivity extends AppCompatActivity {

    private TitleBar titleBar;
    private RoundImageView rivTouxiang;
    private TextView tvUserName;
    private TextView tvDonateStars;
    private TextView tvMyRank;
    private RecyclerView recyclerView;
    private ProjectDonateRecyclerView projectDonateRecyclerView;
    private int projectId;
    private int userId;
    private User user;
    private Donate donate;
    /**
     * @Author:  Infinity
     * @Date:  2018/12/11 0011
     * @Description:  启动活动并传入数据
     */
    public static void actionStars(Context context, int projectId,int userId){
        Intent intent = new Intent(context, ProjectDonateRankActivity.class);
        intent.putExtra("project_id",projectId);
        intent.putExtra("user_id",userId);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_donate_rank);
        ActivityCollector.addActivity(this);
        projectId = getIntent().getIntExtra("project_id",0);
        userId = getIntent().getIntExtra("user_id",0);
        recyclerView = findViewById(R.id.rv_project_donate_rank);
        titleBar = findViewById(R.id.title_donate_rank);
        rivTouxiang = findViewById(R.id.riv_my_donate_touxiang);
        tvUserName = findViewById(R.id.tv_my_name_donate);
        tvDonateStars = findViewById(R.id.tv_my_donated_stars);
        tvMyRank = findViewById(R.id.tv_my_donate_rank);
        user = new UserDAO(this).query(userId);
        donate = new DonateDAO(this).query(userId,projectId);
        initView();
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

    private void initView() {
        projectDonateRecyclerView = new ProjectDonateRecyclerView(recyclerView,this,projectId);
        projectDonateRecyclerView.setLayoutManager();//设置recyclerview
       // rivTouxiang.setImageResource(user.getUserImgRes());
        rivTouxiang.setImageResource(R.drawable.touxiang);
        tvUserName.setText(user.getName());
//        if (donate != null){
//            tvDonateStars.setText(String.valueOf(donate.getDonateStarsNum()));
//            //计算捐赠排名
//            int i = 1;
//            List<Donate> donateList = new DonateDAO(this).getUserScrollData(1,new DonateDAO(this).getCount(),projectId);
//            for (Donate donate:donateList) {
//                if (userId == donate.getUserId()){
//                    tvMyRank.setText("第"+String.valueOf(i)+"名");
//                    break;
//                }
//                i++;
//            }
//        }else {
            tvDonateStars.setText("0");
            tvMyRank.setText("无排名");
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
