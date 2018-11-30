package com.example.administrator.xingyi.more;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.xingyi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/29 0029
 * Author:  Infinity
 */
public class PeopleMoreRecyclerView {
    private RecyclerView recyclerView;
    private Context context;
    private List<More> moreList = new ArrayList<>();

    public PeopleMoreRecyclerView(View view, Context context){
        this.recyclerView = view.findViewById(R.id.rcv_more_touxiang);
        this.context = context;
    }

    public void setLayoutManager(){
        initMore();//初始化列表数据
        //设置RecyclerView管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //recyclerView.addItemDecoration(android.R.attr.listDivider);
        //初始化适配器
        PeopleMoreRecyclerViewAdapter peopleMoreRecyclerViewAdapter = new PeopleMoreRecyclerViewAdapter(moreList);
        recyclerView.setAdapter(peopleMoreRecyclerViewAdapter);
    }

    private void initMore() {
        int touxiang = R.drawable.touxiang;
        String userName = "Infinity丶";
            More more = new More(touxiang,userName);
            moreList.add(more);
        }
}
