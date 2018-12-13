package com.example.administrator.xingyi.project.projectSponser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.CompanyDAO;
import com.example.administrator.xingyi.model.Company;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class ProjectSponserActivity extends AppCompatActivity {

    private TitleBar titleBar;
    private int companyId;
    private ImageView ivSponerDetail;
    private TextView tvSponserName;
    private TextView tvSponserIntroduction;
    private Company company;

    public static void actionStars(Context context, int companyId){
        Intent intent = new Intent(context, ProjectSponserActivity.class);
        intent.putExtra("company_id",companyId);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_sponser);
        ActivityCollector.addActivity(this);
        companyId = getIntent().getIntExtra("company_id",0);
        company = new CompanyDAO(this).query(companyId);
        initView();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_sponser_detail);
        ivSponerDetail = findViewById(R.id.iv_sponser_detail);
        tvSponserName = findViewById(R.id.tv_sponser_name);
        tvSponserIntroduction = findViewById(R.id.tv_company_introduction);
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
//        ivSponerDetail.setImageResource(company.getCompanyImgRes());
//        tvSponserName.setText(company.getCompanyName());
//        tvSponserIntroduction.setText(company.getCompanyIntroduction());
        ivSponerDetail.setImageResource(R.drawable.alashan);
        tvSponserName.setText("阿拉善基金会");
        tvSponserIntroduction.setText(R.string.company_introduction);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
