package com.example.administrator.xingyi.exchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.model.Commodity;

public class CommodityDetailActivity extends AppCompatActivity {
    private Commodity mCommodity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);
        initView();
    }

    private void initView() {
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mCommodity = (Commodity) bundle.get("commodity");
    }
}
