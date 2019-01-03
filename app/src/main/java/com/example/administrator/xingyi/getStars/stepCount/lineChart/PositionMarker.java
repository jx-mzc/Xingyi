package com.example.administrator.xingyi.getStars.stepCount.lineChart;

import android.content.Context;

import com.example.administrator.xingyi.R;
import com.github.mikephil.charting.components.MarkerView;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/18 0018
 * Author:  Infinity
 */
public class PositionMarker extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public PositionMarker(Context context) {
        super(context, R.layout.item_chart_post);
    }
}
