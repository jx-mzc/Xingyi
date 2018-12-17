package com.example.administrator.xingyi.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.AdminDAO;
import com.example.administrator.xingyi.dao.NewsDAO;
import com.example.administrator.xingyi.model.Admin;
import com.example.administrator.xingyi.model.News;
import com.example.administrator.xingyi.news.myAdapter.NewsAdapter;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/27 0027
 * Author:  Infinity
 */
public class NewsFragment extends Fragment{

    //定义成员变量
    ListView lv;
    ArrayList<NewsView> newsViewList=new ArrayList<NewsView>();
    List<News> newsList = new ArrayList<>();
    NewsAdapter na;
    AdminDAO adminDAO;
    NewsDAO newsDAO;
    private Admin admin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        //成员变量初始化
        init(view);
        return view;
    }

    private void init(View view) {
        lv=(ListView)view.findViewById(R.id.news_lv);
        initNews();
    }

    private void initNews(){

        //从数据库中获取数据
//       newsDAO = new NewsDAO(getContext());
//       adminDAO = new AdminDAO(getContext());
//        newsList = newsDAO.getScrollData(1,10);
//        int adminId;
//        int adminResImg;
//        String adminName;
//        String newsTime;
//        String newsContent;
//        int newsPraiseNum;
//        for (News news:newsList) {
//            admin = adminDAO.query(news.getAdminId());
//            adminResImg = admin.getAdminResImg();
//            adminName = admin.getName();
//            newsTime = news.getTime();
//            newsContent = news.getContent();
//            newsPraiseNum = news.getNewsPraiseNum();
//            NewsView newsView = new NewsView(adminResImg,adminName,newsTime,newsContent,String.valueOf(newsPraiseNum));
//            newsViewList.add(newsView);
//        }


        //测试数据
        NewsView newsView = new NewsView(R.drawable.touxiang,"Infinity","2018-12-14","热爱公益，是善行最美的诠释。帮助贫困山区儿童午餐助力完成。","101");
        newsViewList.add(newsView);
        NewsView newsView1 = new NewsView(R.drawable.icon_news_headimage,"我是你的婷","2018-12-13","将在2018年12月14日举行“暖冬捐”活动，您可以捐助不需要的生活用品，我们将回馈您更加有意义的纪念品，您的爱心将会是一缕冬日的阳光，温暖每个人。","101");
        newsViewList.add(newsView1);
        NewsView newsView2 = new NewsView(R.drawable.touxiang,"Infinity","2018-12-12","节省一分零钱，献出一份爱心，温暖世间真情。","101");
        newsViewList.add(newsView2);
        NewsView newsView3 = new NewsView(R.drawable.touxiang,"Infinity","2018-12-11","携手慈善，与爱同行。","101");
        newsViewList.add(newsView3);
        NewsView newsView4 = new NewsView(R.drawable.icon_news_headimage,"我是你的婷","2018-12-10","爱心点燃梦想，真情点燃希望。","101");
        newsViewList.add(newsView4);
        NewsView newsView5 = new NewsView(R.drawable.touxiang,"Infinity","2018-12-9","热爱公益，是善行最美的诠释。帮助贫困山区儿童午餐助力完成。","101");
        newsViewList.add(newsView5);
        //把数据封装到适配器中
        na=new NewsAdapter(newsViewList,getContext());
        lv.setAdapter(na);
    }

}
