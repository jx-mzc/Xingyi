package com.example.administrator.xingyi.getStars.stepCount.lineChart;

import android.content.Context;
import android.graphics.Color;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.customView.MyLineChart;
import com.example.administrator.xingyi.dao.StepNumDetailsDAO;
import com.example.administrator.xingyi.model.StepNumDetails;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/18 0018
 * Author:  Infinity
 */
public class InitLineChart {

    private MyLineChart mLineChart;
    private int userId;
    private String date;
    private int stepNumId;
    private Context context;
    private List<StepNumDetails> stepNumDetailsList;

    public InitLineChart(MyLineChart mLineChart, int userId, String date, int stepNumId,Context context) {
        this.mLineChart = mLineChart;
        this.userId = userId;
        this.date = date;
        this.stepNumId = stepNumId;
        this.context = context;
        stepNumDetailsList = new StepNumDetailsDAO(context).getScrollData(stepNumId,date);
    }

    public void creatLineChart() {
        //1.设置x轴和y轴的点
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 24; i++)
            entries.add(new Entry(i, stepNumDetailsList.get(i).getStepNum()));

        //2.把数据赋值到你的线条
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setDrawCircles(false);
        dataSet.setColor(R.color.tab_checked);//线条颜色
        dataSet.setCircleColor(R.color.tab_checked);//圆点颜色
        dataSet.setLineWidth(1f);//线条宽度
        mLineChart.setScaleEnabled(false);

        //mLineChart.getLineData().getDataSets().get(0).setVisible(true);
        //设置样式
        YAxis rightAxis = mLineChart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        YAxis leftAxis = mLineChart.getAxisLeft();
        //设置图表左边的y轴禁用
        leftAxis.setEnabled(false);
        rightAxis.setAxisMaximum(dataSet.getYMax() * 2);
        leftAxis.setAxisMaximum(dataSet.getYMax() * 2);
        //设置x轴
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#333333"));
        xAxis.setTextSize(11f);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1f);//禁止放大x轴标签重绘
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            list.add(String.valueOf(i)+"时");
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));

        //透明化图例
        Legend legend = mLineChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        legend.setTextColor(Color.WHITE);
        //legend.setYOffset(-2);

        //点击图表坐标监听
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                //查看覆盖物是否被回收
                if (mLineChart.isMarkerAllNull()) {
                    //重新绑定覆盖物
                    createMakerView();
                    //并且手动高亮覆盖物
                    mLineChart.highlightValue(h);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

        //隐藏x轴描述
        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);

        //创建覆盖物
        createMakerView();

        //3.chart设置数据
        LineData lineData = new LineData(dataSet);
        //是否绘制线条上的文字
        lineData.setDrawValues(false);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // refresh
    }

    /**
     * 创建覆盖物
     */
    public void createMakerView() {
        DetailsMarkerView detailsMarkerView = new DetailsMarkerView(context);
        detailsMarkerView.setChartView(mLineChart);
        mLineChart.setDetailsMarkerView(detailsMarkerView);
        mLineChart.setPositionMarker(new PositionMarker(context));
        mLineChart.setRoundMarker(new RoundMarker(context));
    }
}
