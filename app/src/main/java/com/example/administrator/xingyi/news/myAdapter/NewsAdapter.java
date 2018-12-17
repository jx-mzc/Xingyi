package com.example.administrator.xingyi.news.myAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.news.NewsView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {

    ArrayList<NewsView> newsList;
    Context context;

    public NewsAdapter(ArrayList<NewsView> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.news_list_item,null);

        //设置时间栏
        TextView tvTime=(TextView)ll.findViewById(R.id.news_tv_time);
        tvTime.setText(newsList.get(position).getTime());

        //设置内容栏
        TextView tvContext=(TextView)ll.findViewById(R.id.news_tv_content);
        tvContext.setText(newsList.get(position).getNewsContent());

        //设置头像
        ImageView ivHeadImage=(ImageView)ll.findViewById(R.id.news_iv_admin_head);
        ivHeadImage.setImageResource(newsList.get(position).getTouxiang());

        //设置名称
        TextView tvAdminName=(TextView)ll.findViewById(R.id.news_tv_admin_name);
        tvAdminName.setText(newsList.get(position).getAdminName());


        //设置赞个数
        TextView tvPraiseNum=(TextView)ll.findViewById(R.id.news_tv_praise);
        tvPraiseNum.setText(newsList.get(position).getNewsPraiseNum());


        return ll;
    }
}
