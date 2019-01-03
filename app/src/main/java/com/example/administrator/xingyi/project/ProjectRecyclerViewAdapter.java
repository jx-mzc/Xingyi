package com.example.administrator.xingyi.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.customView.ProgressView;
import com.example.administrator.xingyi.login.LoginActivity;
import com.example.administrator.xingyi.model.Project;
import com.example.administrator.xingyi.util.AdjustLogin;

import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/4 0004
 * Author:  Infinity
 */
public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder>{
    private List<ProjectView> list;
    private Context context;

    public ProjectRecyclerViewAdapter(List<ProjectView> list,Context context){
        this.list = list;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View projctView;
        ImageView projectImg;
        TextView projectTitle;
        ProgressView projectProgressView;
        TextView participantCount;
        TextView targetStarsCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projctView = itemView;
            projectImg = (ImageView)itemView.findViewById(R.id.iv_project_item);
            projectTitle = (TextView)itemView.findViewById(R.id.tv_projectview_title_item);
            projectProgressView = (ProgressView)itemView.findViewById(R.id.pv_projectview_item) ;
            participantCount = (TextView)itemView.findViewById(R.id.tv_projectview_participant_item);
            targetStarsCount = (TextView)itemView.findViewById(R.id.tv_target_stars_item);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_project_item,viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.projctView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AdjustLogin.isLogin(context)){//判断是否登录
                    ProjectView projectView = list.get(holder.getAdapterPosition());
                    ProjectActivity.actionStars(context,projectView.getProjectId());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProjectView projectView = list.get(i);
        viewHolder.projectImg.setImageResource(projectView.getProjectImg());
        viewHolder.projectTitle.setText(projectView.getProjectTitle());
        viewHolder.projectProgressView.setTotalAndCurrentCount(projectView.getTargetStarsCount(),projectView.getCurrentStarsCount());
        viewHolder.participantCount.setText(String.valueOf(projectView.getParticipantCount()));
        viewHolder.targetStarsCount.setText(String.valueOf(projectView.getTargetStarsCount()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
