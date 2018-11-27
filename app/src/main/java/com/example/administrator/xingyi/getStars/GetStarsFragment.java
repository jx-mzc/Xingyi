package com.example.administrator.xingyi.getStars;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xingyi.R;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/27 0027
 * Author:  Infinity
 */
public class GetStarsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_getstars,null);
        return view;
    }
}
