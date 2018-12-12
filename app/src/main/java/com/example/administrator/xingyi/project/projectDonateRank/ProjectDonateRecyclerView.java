package com.example.administrator.xingyi.project.projectDonateRank;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.DonateDAO;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.Donate;
import com.example.administrator.xingyi.model.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/11 0011
 * Author:  Infinity
 */
public class ProjectDonateRecyclerView {
    private RecyclerView recyclerView;
    private Context context;
    private int projectId;
    private List<ProjectDonateView> projectDonateViewList = new ArrayList<>();

    public ProjectDonateRecyclerView(RecyclerView recyclerView, Context context,int projectId) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.projectId = projectId;
    }
    public void setLayoutManager(){
        initProjectView();//初始化列表数据
        //设置RecyclerView管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        //recyclerView.addItemDecoration(android.R.attr.listDivider);
        //初始化适配器
        ProjectDonateRecyclerViewAdapter projectRecyclerViewAdapter = new ProjectDonateRecyclerViewAdapter(projectDonateViewList,context);
        recyclerView.setAdapter(projectRecyclerViewAdapter);
    }

    private void initProjectView() {
        DonateDAO donateDAO = new DonateDAO(context);
        UserDAO userDAO = new UserDAO(context);
        List<Donate> donateList;
        User user;
        int userId;
        int userImgRes;
        String userName;
        int donateStars;
        int rank;
        int i = 1;
//        donateList = donateDAO.getUserScrollData(1,10,projectId);
//        for (Donate donate:donateList) {
//            userId = donate.getUserId();
//            user = userDAO.query(userId);
//            userImgRes = user.getUserImgRes();
//            userName = user.getName();
//            donateStars = donate.getDonateStarsNum();
//            rank = i++;
//            ProjectDonateView projectDonateView = new ProjectDonateView(userImgRes,userName,donateStars,rank);
//            projectDonateViewList.add(projectDonateView);
//        }
        for (; i<=20; i++){
            userImgRes = R.drawable.moren_touxiang;
            userName = "用户"+i;
            donateStars = 10000-100*i;
            rank = i;
            ProjectDonateView projectDonateView = new ProjectDonateView(userImgRes,userName,donateStars,rank);
            projectDonateViewList.add(projectDonateView);
        }
    }
}
