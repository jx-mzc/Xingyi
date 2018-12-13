package com.example.administrator.xingyi.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xingyi.ActivityCollector;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.customView.ProgressView;
import com.example.administrator.xingyi.model.Company;
import com.example.administrator.xingyi.model.Organization;
import com.example.administrator.xingyi.model.Project;
import com.example.administrator.xingyi.model.ProjectPraise;
import com.example.administrator.xingyi.project.projectDonate.DonateProjectDialog;
import com.example.administrator.xingyi.project.projectDonateRank.ProjectDonateRankActivity;
import com.example.administrator.xingyi.project.projectMessage.ProjectMessageActivity;
import com.example.administrator.xingyi.project.projectOriginator.ProjectOriginatorActivity;
import com.example.administrator.xingyi.project.projectSponser.ProjectSponserActivity;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;


public class ProjectActivity extends AppCompatActivity implements View.OnClickListener{

    private TitleBar titleBar;
    private int projectId;//公益项目ID
    private Project project;//公益项目
    private Company company;//爱心资助企业
    private ProjectPraise projectPraise;//点赞爱心项目
    private Organization organization;//爱心公益组织
    private ImageView ivProjectImgRes;//公益项目图片
    private TextView tvProjectName;//公益项目名
    private TextView tvProjectIntroduction;//公益项目简述
    private TextView tvProjectUse;//公益项目用途
    private ImageView ivProjectSponser;//资助方图片
    private TextView tvProjectSponser;//公益项目资助方
    private TextView tvProjectSponserDetail;//公益项目资助方详情
    private ImageView ivProjectOriginator;//发起方图片
    private TextView tvProjectOriginator;//公益项目发起方
    private TextView tvProjectOriginatorDetail;//公益项目发起方详情
    private ProgressView pvProject;//公益项目进度条
    private ImageView ivProjectPraise;//点赞公益项目
    private ImageView ivProjectMessage;//留言公益项目
    private ImageView ivProjectShare;//分享公益项目
    private Button btProjectDonate;//捐赠公益项目
    private SharedPreferences pref;
    private int userId;

    /**
     * @Author:  Infinity
     * @Date:  2018/12/7 0007
     * @Description:  启动活动并传入数据
     */
    public static void actionStars(Context context,int projectId){
        Intent intent = new Intent(context, ProjectActivity.class);
        intent.putExtra("project_id",projectId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ActivityCollector.addActivity(this);
//        projectId = getIntent().getIntExtra("project_id",0);
//        project = new ProjectDAO(this).query(projectId);
//        company = new CompanyDAO(this).query(project.getCompanyId());
//        organization = new OrganizationDAO(this).query(project.getOrganizationId());
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private void initView() {
        titleBar = findViewById(R.id.title_project_detail);
        ivProjectImgRes = findViewById(R.id.iv_project);
        tvProjectName = findViewById(R.id.tv_project_title);
        tvProjectIntroduction = findViewById(R.id.tv_project_introduction);
        tvProjectUse = findViewById(R.id.tv_project_use);
        ivProjectSponser = findViewById(R.id.iv_sponser);
        tvProjectSponser = findViewById(R.id.tv_sponser);
        tvProjectSponserDetail = findViewById(R.id.tv_sponser_detail);
        ivProjectOriginator = findViewById(R.id.iv_originator);
        tvProjectOriginator = findViewById(R.id.tv_originator);
        tvProjectOriginatorDetail = findViewById(R.id.tv_originator_detail);
        pvProject = findViewById(R.id.pv_project);
        ivProjectPraise = findViewById(R.id.iv_project_praise);
        ivProjectMessage = findViewById(R.id.iv_project_message);
        ivProjectShare = findViewById(R.id.iv_project_share);
        btProjectDonate = findViewById(R.id.bt_donate);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        userId = pref.getInt("user_id",0);
        //将数据插入布局
//        ivProjectImgRes.setImageResource(project.getProjectImgRes());
//        tvProjectName.setText(project.getProjectName());
//        tvProjectIntroduction.setText(project.getProjectIntroduction());
//        tvProjectUse.setText(project.getProjectUse());
//        ivProjectSponser.setImageResource(company.getCompanyImgRes());
//        tvProjectSponser.setText(company.getCompanyName());
//        ivProjectOriginator.setImageResource(organization.getOrganizationImgRes());
//        tvProjectOriginator.setText(organization.getOrganizationName());
//        pvProject.setTotalAndCurrentCount(project.getProjectNeed(),project.getProjectHave());
//        if (new ProjectPraiseDAO(this).query(userId,projectId) != null){
//            ivProjectPraise.setSelected(true);
//        }

        //设置监听
        tvProjectSponserDetail.setOnClickListener(this);
        tvProjectOriginatorDetail.setOnClickListener(this);
        ivProjectPraise.setOnClickListener(this);
        ivProjectMessage.setOnClickListener(this);
        ivProjectShare.setOnClickListener(this);
        btProjectDonate.setOnClickListener(this);
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
                ProjectDonateRankActivity.actionStars(ProjectActivity.this,projectId,userId);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_sponser_detail:
//                ProjectSponserActivity.actionStars(ProjectActivity.this,company.get_id());
                ProjectSponserActivity.actionStars(ProjectActivity.this,0);
                break;
            case R.id.tv_originator_detail:
//                ProjectOriginatorActivity.actionStars(ProjectActivity.this,organization.get_id());
                ProjectOriginatorActivity.actionStars(ProjectActivity.this,0);
                break;
            case R.id.iv_project_praise:
                if (ivProjectPraise.isSelected()){
                    ivProjectPraise.setSelected(false);
//                    ProjectPraiseDAO projectPraiseDAO = new ProjectPraiseDAO(this);
//                    projectPraise = projectPraiseDAO.query(userId,projectId);
//                    projectPraiseDAO.delete(projectPraise.get_id());//删除点赞记录
                }else {
                    ivProjectPraise.setSelected(true);
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//                    String date = df.format(new Date());
//                    projectPraise = new ProjectPraise();
//                    projectPraise.setUserId(userId);
//                    projectPraise.setProjectId(projectId);
//                    projectPraise.setPraiseTime(date);
//                    ProjectPraiseDAO projectPraiseDAO = new ProjectPraiseDAO(this);
//                    projectPraiseDAO.add(projectPraise);//添加点赞记录
                }
                break;
            case R.id.iv_project_message:
                ProjectMessageActivity.actionStars(ProjectActivity.this,userId,projectId);
                break;
            case R.id.iv_project_share:
                break;
            case R.id.bt_donate:
                View v = LayoutInflater.from(this).inflate(R.layout.donate_dialog_layout,null,false);
                DonateProjectDialog donateProjectDialog = new DonateProjectDialog(v,this,userId,projectId);
                donateProjectDialog.showDialog();
                break;
            default:
                break;
        }
    }
}
