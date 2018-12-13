package com.example.administrator.xingyi.project.projectDonateRank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xingyi.more.RoundImageView;
import com.example.administrator.xingyi.R;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/11 0011
 * Author:  Infinity
 */
public class ProjectDonateRecyclerViewAdapter extends RecyclerView.Adapter<ProjectDonateRecyclerViewAdapter.ViewHolder>{

    private List<ProjectDonateView> list;
    Context context;

    public ProjectDonateRecyclerViewAdapter(List<ProjectDonateView> list,Context context){
        this.list = list;
        this.context = context;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        View projectDonateView;
        RoundImageView rivTouxiang;
        TextView tvDonateName;
        TextView tvDonateStars;
        TextView tvDonateRank;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectDonateView = itemView;
            rivTouxiang = (RoundImageView)itemView.findViewById(R.id.riv_donate_touxiang);
            tvDonateName = (TextView)itemView.findViewById(R.id.tv_donate_name);
            tvDonateStars = (TextView)itemView.findViewById(R.id.tv_donated_stars);
            tvDonateRank = (TextView)itemView.findViewById(R.id.tv_donate_rank);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_donate_rank_item,viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.projectDonateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProjectDonateView projectDonateView = list.get(i);
        viewHolder.rivTouxiang.setImageResource(projectDonateView.getTouxiang());
        viewHolder.tvDonateName.setText(projectDonateView.getDonateName());
        viewHolder.tvDonateStars.setText(String.valueOf(projectDonateView.getDonateStars()));
        viewHolder.tvDonateRank.setText(String.valueOf(projectDonateView.getDonateRank()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
