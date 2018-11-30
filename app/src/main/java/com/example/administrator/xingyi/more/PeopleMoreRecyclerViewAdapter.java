package com.example.administrator.xingyi.more;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xingyi.R;

import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/29 0029
 * Author:  Infinity
 */
public class PeopleMoreRecyclerViewAdapter extends RecyclerView.Adapter<PeopleMoreRecyclerViewAdapter.ViewHolder>{

    private List<More> list;

    public PeopleMoreRecyclerViewAdapter(List<More> list){
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View moreView;
        ImageView touxiang;
        TextView userName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moreView = itemView;
            touxiang = (ImageView)itemView.findViewById(R.id.item_more_round_img);
            userName = (TextView)itemView.findViewById(R.id.item_more_username);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.people_recyclerview_more_item,viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.moreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                More more = list.get(position);
                Toast.makeText(view.getContext(),"你点击了："+more.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        More more = list.get(i);
        viewHolder.touxiang.setImageResource(more.getLeftIcon());
        viewHolder.userName.setText(more.getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
