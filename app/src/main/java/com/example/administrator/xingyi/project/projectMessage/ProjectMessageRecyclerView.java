package com.example.administrator.xingyi.project.projectMessage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.ProjectMessageDAO;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.ProjectMessage;
import com.example.administrator.xingyi.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/13 0013
 * Author:  Infinity
 */
public class ProjectMessageRecyclerView {
    private RecyclerView recyclerView;
    private Context context;
    private int projectId;
    private List<ProjectMessageView> projectMessageViewList = new ArrayList<>();

    public ProjectMessageRecyclerView(RecyclerView recyclerView, Context context, int projectId) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.projectId = projectId;
    }

    public void setLayoutManager() {
        initProjectView();//初始化列表数据
        //设置RecyclerView管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        //初始化适配器
        ProjectMessageRecyclerViewAdapter projectMessageRecyclerViewAdapter = new ProjectMessageRecyclerViewAdapter(projectMessageViewList,context);
        recyclerView.setAdapter(projectMessageRecyclerViewAdapter);
    }

    private void initProjectView() {
        ProjectMessageDAO projectMessageDAO = new ProjectMessageDAO(context);
        UserDAO userDAO = new UserDAO(context);
        User user;
        List<ProjectMessage> projectMessageList;
        int userId;
        int userImgRes;
        String userName;
        String messageContent;
        String messageTime;
//        projectMessageList = projectMessageDAO.getUserScrollData(projectId);
//        for (ProjectMessage projectMessage:projectMessageList) {
//            userId = projectMessage.getUserId();
//            user = userDAO.query(userId);
//            userImgRes = user.getUserImgRes();
//            userName = user.getName();
//            messageContent = projectMessage.getContent();
//            messageTime = projectMessage.getMessageTime();
//            ProjectMessageView projectMessageView = new ProjectMessageView(userImgRes,userName,messageContent,messageTime);
//            projectMessageViewList.add(projectMessageView);
//        }
        for (int i=1;i<=20;i++){
            userImgRes = R.drawable.moren_touxiang;
            userName = "用户"+i;
            messageContent = "支持公益，加油！";
            messageTime = "2018-12-"+(20-i);
            ProjectMessageView projectMessageView = new ProjectMessageView(userImgRes,userName,messageContent,messageTime);
            projectMessageViewList.add(projectMessageView);
        }
    }
}
