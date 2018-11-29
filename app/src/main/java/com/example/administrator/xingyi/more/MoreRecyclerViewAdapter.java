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
 * Date:  2018/11/28 0028
 * Author:  Infinity
 */
public class MoreRecyclerViewAdapter extends RecyclerView.Adapter<MoreRecyclerViewAdapter.ViewHolder> {

    private List<More> list;

    public MoreRecyclerViewAdapter(List<More> list){
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View moreView;
        ImageView leftIcon;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moreView = itemView;
            leftIcon = (ImageView)itemView.findViewById(R.id.item_more_icon);
            text = (TextView)itemView.findViewById(R.id.item_more_text);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_more_item,viewGroup, false);
        final ViewHolder holer = new ViewHolder(view);
        holer.moreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holer.getAdapterPosition();
                More more = list.get(position);
                Toast.makeText(view.getContext(),"你点击了："+more.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return holer;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        More more = list.get(i);
        viewHolder.leftIcon.setImageResource(more.getLeftIcon());
        viewHolder.text.setText(more.getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
