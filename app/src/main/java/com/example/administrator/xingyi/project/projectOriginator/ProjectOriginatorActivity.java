package com.example.administrator.xingyi.project.projectOriginator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.OrganizationDAO;
import com.example.administrator.xingyi.model.Organization;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class ProjectOriginatorActivity extends AppCompatActivity {

    private TitleBar titleBar;
    private int organizationId;
    private ImageView ivOriginatorDetail;
    private TextView tvOriginatorName;
    private TextView tvOriginatorIntroduction;
    private Organization organization;

    public static void actionStars(Context context, int organizationId){
        Intent intent = new Intent(context, ProjectOriginatorActivity.class);
        intent.putExtra("organization_id",organizationId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_originator);
        ActivityCollector.addActivity(this);
        organizationId = getIntent().getIntExtra("organization_id",0);
        organization = new OrganizationDAO(this).query(organizationId);
        initView();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_originator_detail);
        ivOriginatorDetail = findViewById(R.id.iv_originator_detail);
        tvOriginatorName = findViewById(R.id.tv_originator_name);
        tvOriginatorIntroduction = findViewById(R.id.tv_organization_introduction);
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
//        ivOriginatorDetail.setImageResource(organization.getOrganizationImgRes());
//        tvOriginatorName.setText(organization.getOrganizationName());
//        tvOriginatorIntroduction.setText(organization.getOrganizationIntroduction());
        ivOriginatorDetail.setImageResource(R.drawable.siyecao);
        tvOriginatorName.setText("四叶草公益");
        tvOriginatorIntroduction.setText(R.string.organization_introduction);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
