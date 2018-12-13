package com.example.administrator.xingyi.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.xingyi.R;
import com.hjq.bar.TitleBar;
import com.zhouwei.mzbanner.MZBannerView;


/**
 * Project Name:  Xingyi
 * Date:  2018/11/27 0027
 * Author:  Infinity
 */
public class ProjectFragment extends Fragment {

    private MZBannerView mzBannerView;
    private BannerView bannerView;
    private ProjectRecyclerView projectRecyclerView;

    public ProjectFragment(){
        super();
    }

    @Override
    public void onResume() {
        super.onResume();
        mzBannerView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mzBannerView.pause();//暂停轮播
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project,null);
        mzBannerView = (MZBannerView)view.findViewById(R.id.project_banner);
        projectRecyclerView = new ProjectRecyclerView(view,getContext());
        bannerView = new BannerView(mzBannerView,getContext());
        bannerView.setBannerView();//利用框架设置轮播图
        mzBannerView.start();//开始轮播
        projectRecyclerView.setLayoutManager();
        return view;
    }
}
