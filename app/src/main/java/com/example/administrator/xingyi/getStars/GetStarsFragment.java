package com.example.administrator.xingyi.getStars;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.getStars.clocks.ClocksActivity;
import com.example.administrator.xingyi.getStars.stepCount.StepCountActivity;
import com.hjq.bar.TitleBar;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/27 0027
 * Author:  Infinity
 */
public class GetStarsFragment extends Fragment {

    private RelativeLayout rlStepCount;
    private RelativeLayout rlClocks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_getstars,null);
        rlStepCount = view.findViewById(R.id.rv_getstars_step);
        rlClocks = view.findViewById(R.id.rv_getstars_clocks);
        rlStepCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), StepCountActivity.class);
                startActivity(intent);
            }
        });
        rlClocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ClocksActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
