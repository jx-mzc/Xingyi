package com.example.administrator.xingyi.project.projectMessage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.ProjectMessageDAO;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.ProjectMessage;
import com.example.administrator.xingyi.model.User;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectMessageActivity extends AppCompatActivity {

    private int userId;
    private int projectId;
    private TitleBar titleBar;
    private RecyclerView recyclerView;
    private EditText etProjectMessage;
    private TextView tvSendMessage;
    private ProjectMessageRecyclerView projectMessageRecyclerView;
    private ProjectMessage projectMessage;
    private long messageCount;

    public static void actionStars(Context context,int userId,int projectId){
        Intent intent = new Intent(context, ProjectMessageActivity.class);
        intent.putExtra("user_id",userId);
        intent.putExtra("project_id",projectId);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_message);
        ActivityCollector.addActivity(this);
        projectId = getIntent().getIntExtra("project_id",0);
        userId = getIntent().getIntExtra("user_id",0);
        initView();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_project_message);
        recyclerView = findViewById(R.id.rv_project_message);
        etProjectMessage = findViewById(R.id.et_project_message);
        tvSendMessage = findViewById(R.id.tv_send_project_message);

//        messageCount = new ProjectMessageDAO(this).getUserCount(projectId);
        messageCount = 20;
        titleBar.setTitle(String.valueOf(messageCount)+"条留言");
        projectMessageRecyclerView = new ProjectMessageRecyclerView(recyclerView,this,projectId);
        projectMessageRecyclerView.setLayoutManager();

        //标题点击事件
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
        //监听文本输入框
        etProjectMessage.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length()>0){
                    tvSendMessage.setEnabled(true);
                    tvSendMessage.setTextColor(getResources().getColor(R.color.tab_checked,null));
                }else {
                    tvSendMessage.setEnabled(false);
                    tvSendMessage.setTextColor(getResources().getColor(R.color.gray,null));
                }
            }
        });
        //设置发送监听
        tvSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etProjectMessage.getText().toString();//获取输入文本信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String date = df.format(new Date());
//                ProjectMessage projectMessage = new ProjectMessage(0,userId,projectId,content,date);
//                ProjectMessageDAO projectMessageDAO = new ProjectMessageDAO(ProjectMessageActivity.this);
//                projectMessageDAO.add(projectMessage);


                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //软键盘弹出
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                //刷新界面信息
                etProjectMessage.setText("");
                messageCount +=1;
                titleBar.setTitle(String.valueOf(messageCount)+"条留言");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
