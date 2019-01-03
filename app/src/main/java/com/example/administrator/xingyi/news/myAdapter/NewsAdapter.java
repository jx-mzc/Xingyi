package com.example.administrator.xingyi.news.myAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.news.NewsEvaluteActivity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {


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
        final TextView tvPraiseNum=(TextView)ll.findViewById(R.id.news_tv_praise);
        tvPraiseNum.setText(newsList.get(position).getNewsPraiseNum());

        //点击“+”号
        final ImageView ivAdd=(ImageView)ll.findViewById(R.id.news_iv_icon_add);
        ivAdd.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0,0,0,"分享给好友");
                menu.add(0,1,0,"分享到朋友圈");
                menu.add(0,2,1,"建立卡片");
            }
        });

        //点击赞
        final ImageView ivPraise=(ImageView)ll.findViewById(R.id.news_iv_icon_praise);
        ivPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ivPraise.isSelected()){
                    ivPraise.setImageResource(R.drawable.icon_news_praise);
                    ivPraise.setSelected(false);
                    tvPraiseNum.setText("101");
                }else {
                    ivPraise.setImageResource(R.drawable.icon_news_praise_check);
                    ivPraise.setSelected(true);
                    tvPraiseNum.setText("102");
                }
        }
        });

        //点击评论，跳转到评论界面
        ImageView ivEvalute=(ImageView)ll.findViewById(R.id.news_iv_evalute);
        ivEvalute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("headImage",newsList.get(position).getTouxiang());
                intent.putExtra("nickname",newsList.get(position).getAdminName());
                intent.putExtra("time",newsList.get(position).getTime());
                intent.putExtra("content",newsList.get(position).getNewsContent());
                intent.setClass(context, NewsEvaluteActivity.class);
                context.startActivity(intent);
            }
        });


        return ll;
    }

}
