package com.example.administrator.xingyi;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.administrator.xingyi.dao.MyDatabaseHelper;
import com.example.administrator.xingyi.exchange.ExchangeFragment;
import com.example.administrator.xingyi.getStars.GetStarsFragment;
import com.example.administrator.xingyi.more.MoreFragment;
import com.example.administrator.xingyi.news.NewsFragment;
import com.example.administrator.xingyi.project.ProjectFragment;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCollector.addActivity(this);
        //myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
       // myDatabaseHelper.getWritableDatabase();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_object:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_getstars:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_news:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_exchange:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.item_more:
                                viewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProjectFragment());
        adapter.addFragment(new GetStarsFragment());
        adapter.addFragment(new NewsFragment());
        adapter.addFragment(new ExchangeFragment());
        adapter.addFragment(new MoreFragment());
        viewPager.setAdapter(adapter);
    }
}
