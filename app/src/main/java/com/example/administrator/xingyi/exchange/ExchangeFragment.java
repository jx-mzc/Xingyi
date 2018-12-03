package com.example.administrator.xingyi.exchange;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.common.RecyclerItemDecoration;
import com.example.administrator.xingyi.exchange.adapter.CommodityAdapter;
import com.example.administrator.xingyi.model.Commodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/27 0027
 * Author:  Infinity
 */
public class ExchangeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private List<Commodity> commodities;
    private CommodityAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initCommodities();
        mRecyclerView = view.findViewById(R.id.exchange_recyclerview);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(10,2));
        mAdapter = new CommodityAdapter(commodities);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initCommodities() {
        commodities = new ArrayList<>();
        Commodity commodity = new Commodity("小红帽",R.mipmap.ic_launcher, "又暖又红的小红帽", 5);
        commodities.add(commodity);
        commodities.add(commodity);
        commodities.add(commodity);
        commodities.add(commodity);
        commodities.add(commodity);
        commodities.add(commodity);
        commodities.add(commodity);
        commodities.add(commodity);
        commodities.add(commodity);
        commodities.add(commodity);

    }
}
