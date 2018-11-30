package com.example.administrator.xingyi.more;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private View view;
    private MoreRecyclerView moreRecyclerView;
    private PeopleMoreRecyclerView peopleMoreRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more,null);
        moreRecyclerView = new MoreRecyclerView(view,getContext());
        peopleMoreRecyclerView = new PeopleMoreRecyclerView(view,getContext());
        moreRecyclerView.setLayoutManager();
        peopleMoreRecyclerView.setLayoutManager();
        return view;
    }

}
