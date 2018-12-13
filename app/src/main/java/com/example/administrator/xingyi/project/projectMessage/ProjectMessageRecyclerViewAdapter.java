package com.example.administrator.xingyi.project.projectMessage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.more.RoundImageView;

import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/13 0013
 * Author:  Infinity
 */
public class ProjectMessageRecyclerViewAdapter extends RecyclerView.Adapter<ProjectMessageRecyclerViewAdapter.ViewHolder>{

    private List<ProjectMessageView> list;
    Context context;

    public ProjectMessageRecyclerViewAdapter(List<ProjectMessageView> list, Context context) {
        this.list = list;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View projectMessageView;
        RoundImageView rivTouxiang;
        TextView tvMessageUserName;
        TextView tvMessageContent;
        TextView tvMessageDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectMessageView = itemView;
            rivTouxiang = itemView.findViewById(R.id.riv_project_message);
            tvMessageUserName = itemView.findViewById(R.id.tv_project_message_name);
            tvMessageContent = itemView.findViewById(R.id.tv_project_message_content);
            tvMessageDate = itemView.findViewById(R.id.tv_project_message_time);
        }
    }
    @NonNull
    @Override
    public ProjectMessageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_project_message_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.projectMessageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectMessageRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        ProjectMessageView projectMessageView = list.get(i);
        viewHolder.rivTouxiang.setImageResource(projectMessageView.getTouxiang());
        viewHolder.tvMessageUserName.setText(projectMessageView.getUserName());
        viewHolder.tvMessageContent.setText(projectMessageView.getUserMessage());
        viewHolder.tvMessageDate.setText(projectMessageView.getMessageDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
