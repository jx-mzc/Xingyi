package com.example.administrator.xingyi.news.myAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.xingyi.R;

import java.util.ArrayList;
import java.util.Map;

public class EvaluteAdapter extends BaseAdapter {
    ArrayList<Map<String,Object>> list;
    Context context;

    public EvaluteAdapter(ArrayList<Map<String, Object>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout ll=(LinearLayout)View.inflate(context, R.layout.evalute_list_item,null);
        //设置名称
        TextView tvName=(TextView)ll.findViewById(R.id.news_evalute_item_nickname);
        tvName.setText(list.get(position).get("eNickname").toString());
        //设置内容栏
        TextView tvContext=(TextView)ll.findViewById(R.id.news_evalute_item_content);
        tvContext.setText(list.get(position).get("eContent").toString());

        return ll;
    }
}
