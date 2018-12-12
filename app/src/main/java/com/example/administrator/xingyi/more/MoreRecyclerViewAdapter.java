package com.example.administrator.xingyi.more;

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
import android.widget.Toast;

import com.example.administrator.xingyi.MainActivity;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.login.LoginActivity;
import com.example.administrator.xingyi.more.advice.AdviceActivity;

import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/28 0028
 * Author:  Infinity
 */
public class MoreRecyclerViewAdapter extends RecyclerView.Adapter<MoreRecyclerViewAdapter.ViewHolder> {

    private List<More> list;
    Context context;
    private SharedPreferences pref;

    public MoreRecyclerViewAdapter(List<More> list, Context context){
        this.list = list;
        this.context = context;
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
        final ViewHolder holder = new ViewHolder(view);
        holder.moreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                pref = PreferenceManager.getDefaultSharedPreferences(context);
                if (pref.getBoolean("logining",false)){//判断是否登录
                    Intent intent = null;
                    switch (position){
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            intent = new Intent(context,AdviceActivity.class);
                            context.startActivity(intent);
                            break;
                        case 5:
                            break;
                        default:
                            break;
                    }
                }else {
                    AlertDialog alertDialog;
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(context);
                    alertDialog = builder.setTitle("你还未登录")
                            .setMessage("请登录或者注册")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setPositiveButton("去登录或注册", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    context.startActivity(intent);
                                }
                            }).create();
                    alertDialog.show();
                }

            }
        });
        return holder;
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
