package com.example.administrator.xingyi.more;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xingyi.R;
import com.hjq.bar.TitleBar;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/27 0027
 * Author:  Infinity
 */
public class MoreFragment extends Fragment {

    private TitleBar titleBar;
    private MoreRecyclerView moreRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more,null);
        titleBar = (TitleBar)view.findViewById(R.id.title_more);
        titleBar.setBackgroundColor(getResources().getColor(R.color.tab_checked,null));
        moreRecyclerView = new MoreRecyclerView(view,getContext());
        moreRecyclerView.setLayoutManager();
        return view;
    }
}
