package com.example.administrator.xingyi.more;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.xingyi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/28 0028
 * Author:  Infinity
 */
public class MoreRecyclerView {
    private RecyclerView recyclerView;
    private Context context;
    private List<More> moreList = new ArrayList<>();

    public MoreRecyclerView(View view, Context context){
        this.recyclerView = view.findViewById(R.id.rcv_more);
        this.context = context;
    }

    public void setLayoutManager(){
        initMore();//初始化列表数据
        //设置RecyclerView管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //recyclerView.addItemDecoration(android.R.attr.listDivider);
        //初始化适配器
        MoreRecyclerViewAdapter moreRecyclerViewAdapter = new MoreRecyclerViewAdapter(moreList);
        recyclerView.setAdapter(moreRecyclerViewAdapter);
    }

    private void initMore() {
        int[] leftIcon = {R.drawable.ic_my_message,R.drawable.ic_stars,R.drawable.ic_donated,R.drawable.ic_ren,
        R.drawable.ic_fankui,R.drawable.ic_setting,};
        String[] moreText = context.getResources().getStringArray(R.array.more);
        for (int i=0;i<moreText.length;i++){
            More more = new More(leftIcon[i],moreText[i]);
            moreList.add(more);
        }
    }
}
