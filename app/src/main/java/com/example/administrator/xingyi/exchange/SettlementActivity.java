package com.example.administrator.xingyi.exchange;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.ExchangeDAO;
import com.example.administrator.xingyi.dao.ExchangeDetailsDAO;
import com.example.administrator.xingyi.dao.ShoppingCartDAO;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.exchange.adapter.OrderAdapter;
import com.example.administrator.xingyi.model.Exchange;
import com.example.administrator.xingyi.model.ExchangeDetails;
import com.example.administrator.xingyi.model.ShoppingCartItem;
import com.example.administrator.xingyi.model.User;
import com.example.administrator.xingyi.util.SharedPreferencesUtils;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SettlementActivity extends AppCompatActivity implements View.OnClickListener {
    private TitleBar titleBar;
    private TextView receiver, telephone, address, tv_submit_settlement, tv_show_price;
    private View addressRelativeLayout;
    private RecyclerView recyclerView;
    private List<ShoppingCartItem> orderList;
    private OrderAdapter orderAdapter;
    private double totalPrice = 0.00;
    private User mUser;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        initView();
        initData();
    }

    private void initData() {
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        mUser = new UserDAO(this).query(sp.getString("user_name",""), sp.getString("password", ""));
        orderList = (List<ShoppingCartItem>) getIntent().getExtras().getSerializable("orderList");
        orderAdapter = new OrderAdapter(this, orderList);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(orderAdapter);
        for (int i=0; i < orderList.size(); i++){
            totalPrice += orderList.get(i).getCommodityStars() * orderList.get(i).getCount();
        }
        tv_show_price.setText("合计:" + totalPrice);
        receiver.setText(mUser.getName());
        telephone.setText(String.valueOf(mUser.getTel()));
        address.setText(mUser.getAddress());
    }

    private void initView() {
        titleBar = findViewById(R.id.title_order);
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
        receiver = findViewById(R.id.receiver);
        telephone = findViewById(R.id.telephone);
        address = findViewById(R.id.address_detail);
        tv_submit_settlement = findViewById(R.id.tv_submit_settlement);
        tv_show_price = findViewById(R.id.tv_show_price);
        addressRelativeLayout = findViewById(R.id.address);
        recyclerView = findViewById(R.id.settlement_recyclerview);
        addressRelativeLayout.setOnClickListener(this);
        tv_submit_settlement.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_submit_settlement:
                //兑换单入库
                Exchange exchange = new Exchange();
                exchange.setUserId(mUser.get_id());
                exchange.setAddress(mUser.getAddress());
                exchange.setCostStars((int) totalPrice);
                exchange.setReceiver(mUser.getName());
                exchange.setExchangeTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                exchange.setState("未知");
                exchange.setTel(mUser.getTel());
                ExchangeDAO exchangeDAO = new ExchangeDAO(this);
                exchangeDAO.add(exchange);
                //兑换单明细入库
                int exchangeId = exchangeDAO.getExchangeScrollData((int) exchangeDAO.getExchangeCount(mUser.get_id()),1, mUser.get_id()).get(0).get_id();
                ExchangeDetailsDAO exchangeDetailsDAO = new ExchangeDetailsDAO(this);
                ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO(this);
                int ids[] = new int[orderList.size()];
                for (int i = 0; i<orderList.size(); i++){

                    ExchangeDetails exchangeDetails = new ExchangeDetails();
                    exchangeDetails.setExchangeId(exchangeId);
                    exchangeDetails.setCommodityId(orderList.get(i).getCommodity_id());
                    exchangeDetails.setCommodityName(orderList.get(i).getCommodityName());
                    exchangeDetails.setNum(orderList.get(i).getCount());
                    exchangeDetailsDAO.add(exchangeDetails);
                    if (orderList.get(i).get_id()!=0){
                        shoppingCartDAO.delete(orderList.get(i).get_id());
                    }

                }

                Toast.makeText(SettlementActivity.this,"兑换单入库成功！", Toast.LENGTH_SHORT).show();
                //删除购物车项

                finish();
                break;

            case R.id.address:
                break;
        }
    }
}
