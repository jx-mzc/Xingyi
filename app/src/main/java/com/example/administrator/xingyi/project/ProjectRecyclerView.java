package com.example.administrator.xingyi.project;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.DonateDAO;
import com.example.administrator.xingyi.dao.ProjectDAO;
import com.example.administrator.xingyi.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/4 0004
 * Author:  Infinity
 */
public class ProjectRecyclerView {
    private RecyclerView recyclerView;
    private Context context;
    private List<ProjectView> projectViewList = new ArrayList<>();

    public ProjectRecyclerView(View view, Context context){
        this.recyclerView = view.findViewById(R.id.rv_project);
        this.context = context;
    }
    public void setLayoutManager(){
        initProjectView();//初始化列表数据
        //设置RecyclerView管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false

        );
        //recyclerView.addItemDecoration(android.R.attr.listDivider);
        //初始化适配器
        ProjectRecyclerViewAdapter projectRecyclerViewAdapter = new ProjectRecyclerViewAdapter(projectViewList,context);
        recyclerView.setAdapter(projectRecyclerViewAdapter);
    }

    private void initProjectView() {
        ProjectDAO projectDAO = new ProjectDAO(context);
        DonateDAO donateDAO = new DonateDAO(context);
        List<Project> projectList;
        int projectId;
        int projectImg;
        String projectTitle;
        long targetStarsCount;
        long currentStarsCount;
        long participantCount;
//        projectList = projectDAO.getScrollData(1,10);
//        for (Project project:projectList) {
//            projectId = donateDAO.query(project.get_id()).getProjectId();
//            projectImg = R.drawable.timg;
//            projectTitle = project.getProjectName();
//            targetStarsCount = project.getProjectNeed();
//            currentStarsCount = donateDAO.getStarsSum(projectId);
//            participantCount = donateDAO.getUserCount(projectId);
//            ProjectView projectView = new ProjectView(projectId,projectImg,projectTitle,targetStarsCount,currentStarsCount,participantCount);
//            projectViewList.add(projectView);
//        }
        for (int i =1;i<=6;i++){
            projectId = i;
            projectImg = R.drawable.timg2;
            projectTitle = "公益项目"+i;
            targetStarsCount = 200*i;
            currentStarsCount = 100*i;
            participantCount = 20*i;
            ProjectView projectView = new ProjectView(projectId,projectImg,projectTitle,targetStarsCount,currentStarsCount,participantCount);
            projectViewList.add(projectView);
        }
    }
}
