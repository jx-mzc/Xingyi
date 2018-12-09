package com.example.administrator.xingyi.exchange;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.ShoppingCartDAO;
import com.example.administrator.xingyi.exchange.adapter.ShoppingCartAdapter;
import com.example.administrator.xingyi.model.ShoppingCart;
import com.example.administrator.xingyi.model.ShoppingCartItem;
import com.example.administrator.xingyi.model.User;
import com.example.administrator.xingyi.more.person.EditPersonActivity;
import com.example.administrator.xingyi.more.person.PersonActivity;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener, ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface {

    private TitleBar titleBar;
    public TextView tv_settlement, tv_show_price;
    private TextView tv_all_check;
    private CheckBox ck_all;
    private ListView list_shopping_cart;
    private ShoppingCartAdapter shoppingCartAdapter;
    private boolean flag = false;
    private List<ShoppingCartItem> shoppingCartBeanList = new ArrayList<>();
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private User mUser;
    private ShoppingCartDAO shoppingCartDAO;
    /*
    批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        initView();
        initData();
    }


    protected void initView() {
        titleBar = findViewById(R.id.title_shoppingcart);
        list_shopping_cart = findViewById(R.id.list_shopping_cart);
//        list_shopping_cart.setOnItemClickListener(this);
        ck_all = findViewById(R.id.ck_all);
        ck_all.setOnClickListener(this);
//        ck_all.setOnCheckedChangeListener(this);
        tv_show_price = findViewById(R.id.tv_show_price);
        tv_settlement = findViewById(R.id.tv_settlement);
        tv_settlement.setOnClickListener(this);
        shoppingCartAdapter = new ShoppingCartAdapter(this);
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        list_shopping_cart.setAdapter(shoppingCartAdapter);
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
                flag = !flag;
                if (flag) {
                    titleBar.getRightView().setText("完成");
                    tv_show_price.setVisibility(View.GONE);
                    tv_settlement.setText("删除");
                    shoppingCartAdapter.isShow(false);
                } else {
                    titleBar.getRightView().setText("编辑");
                    statistics();
                    shoppingCartAdapter.isShow(true);
                }
            }
        });

    }

    protected void initData() {
        mUser = (User) getIntent().getExtras().get("mUser");
        shoppingCartDAO = new ShoppingCartDAO(this);
        shoppingCartBeanList = shoppingCartDAO.getCommodityScrollData(1, (int) shoppingCartDAO.getCount());
        shoppingCartAdapter.setShoppingCartList(shoppingCartBeanList);
//        for (int i = 0; i < 6; i++) {
//            ShoppingCartItem shoppingCartBean = new ShoppingCartItem();
//            shoppingCartBean.setCommodityName("高端大气上档次的T桖");
//            shoppingCartBean.setCommodityStars(60);
//            shoppingCartBean.setCount(2);
//            shoppingCartBeanList.add(shoppingCartBean);
//        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选按钮
            case R.id.ck_all:
                if (shoppingCartBeanList.size() != 0) {
                    if (ck_all.isChecked()) {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;
            case R.id.tv_settlement:
                if (flag){
                    AlertDialog alert = new AlertDialog.Builder(this).create();
                    alert.setTitle("操作提示");
                    alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //删除操作
                                    for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                                        ShoppingCartItem shoppingCartBean = shoppingCartBeanList.get(i);
                                        if (shoppingCartBean.isChoosed()) {
                                            shoppingCartDAO.delete(shoppingCartBean.get_id());
                                            childDelete(i);
                                            i--;
                                        }
                                    }
                                    shoppingCartAdapter.notifyDataSetChanged();
                                }
                            });
                    alert.show();
                }else {
                    //结算操作
                    List<ShoppingCartItem> orderList = new ArrayList<>();
                    for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                        ShoppingCartItem shoppingCartBean = shoppingCartBeanList.get(i);
                        if (shoppingCartBean.isChoosed()) {
                            orderList.add(shoppingCartBean);
                        }
                    }
                    if (orderList.size() != 0) {
                        Intent intent = new Intent(ShoppingCartActivity.this, SettlementActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("orderList", (Serializable) orderList);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                break;

        }
    }

    /**
     *单选
     *
     *@param position  组元素位置
     *@param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {

        shoppingCartBeanList.get(position).setChoosed(isChecked);

        if (isAllCheck())
            ck_all.setChecked(true);
        else
            ck_all.setChecked(false);

        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }


    /**
     *遍历list集合
     *
     *@return
     */
    private boolean isAllCheck() {

        for (ShoppingCartItem group : shoppingCartBeanList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    public void compute(){

    }

    /*
    * 统计操作
    * 先判断编辑状态
    * 1.先清空全局计数器<br>
    * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
    * 3.给底部的textView进行数据填充
    */
    public void statistics() {
        if(flag){
            tv_show_price.setVisibility(View.GONE);
            tv_settlement.setText("删除");
        }else {
            totalCount = 0;
            totalPrice = 0.00;
            for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                ShoppingCartItem shoppingCartBean = shoppingCartBeanList.get(i);
                if (shoppingCartBean.isChoosed()) {
                    totalCount++;
                    totalPrice += shoppingCartBean.getCommodityStars() * shoppingCartBean.getCount();
                }
            }
            tv_show_price.setVisibility(View.VISIBLE);
            tv_show_price.setText("合计:" + totalPrice);
            tv_settlement.setText("结算(" + totalCount + ")");
        }

    }


    /**
     *增加
     *
     *@param position      组元素位置
     *@param showCountView 用于展示变化后数量的View
     *@param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartItem shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     *删减
     *
     *@param position      组元素位置
     *@param showCountView 用于展示变化后数量的View
     *@param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartItem shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();

    }

    /**
      *删除
      *
      *@param position
      */
    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
//        shoppingCartAdapter.notifyDataSetChanged();

//        statistics();

    }  
}
