package com.example.administrator.xingyi.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.news.myAdapter.EvaluteAdapter;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsEvaluteActivity extends AppCompatActivity {

    //定义成员变量
    TitleBar titleBar;
    TextView tvNickname,tvTime,tvContent;
    ListView lv;
    EditText etContent;
    Button btSend;
    ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_evalute);
        //成员变量初始化
        init();
        //获得上一个页面intent传来的值
        getValues();
        //准备数据
        initData();
        //事件监听
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etContent.getText().toString()==null||etContent.getText().toString().equals("")){
                    Toast.makeText(NewsEvaluteActivity.this,"请输入评论内容",Toast.LENGTH_SHORT).show();
                }else{
                    etContent.setText("");
                    Toast.makeText(NewsEvaluteActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //适配器
        EvaluteAdapter ea=new EvaluteAdapter(list,getApplicationContext());
        lv.setAdapter(ea);
    }

    private void initData() {
        for(int i=1;i<=20;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("eNickname","评论者"+i+":");
            map.put("eContent","评论内容"+i);
            list.add(map);
        }
    }

    private void init() {
        titleBar = findViewById(R.id.title_news_message);
        tvNickname=(TextView)this.findViewById(R.id.news_evalute_tv_admin_name);
        tvTime=(TextView)this.findViewById(R.id.news_evalute_tv_time);
        tvContent=(TextView)this.findViewById(R.id.news_evalute_tv_content);
        lv=(ListView)this.findViewById(R.id.news_evalute_lv);
        etContent=(EditText)this.findViewById(R.id.news_evalute_et_content);
        btSend=(Button)this.findViewById(R.id.news_evalute_bt_send);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    //获取动态信息值
    private void getValues(){
        Intent intent=getIntent();
        tvNickname.setText(intent.getStringExtra("nickname"));
        tvContent.setText(intent.getStringExtra("time"));
        tvContent.setText(intent.getStringExtra("content"));
    }
}
