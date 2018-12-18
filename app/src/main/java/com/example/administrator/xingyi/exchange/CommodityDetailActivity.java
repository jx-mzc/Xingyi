package com.example.administrator.xingyi.exchange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.ShoppingCartDAO;
import com.example.administrator.xingyi.dao.UserDAO;
import com.example.administrator.xingyi.model.Commodity;
import com.example.administrator.xingyi.model.ShoppingCart;
import com.example.administrator.xingyi.model.ShoppingCartItem;
import com.example.administrator.xingyi.model.User;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommodityDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public final static int[] COMMODITY_IMAGES = new int[]{R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04, R.drawable.img05, R.drawable.img06, R.drawable.img07};
    //商品图片
    private ImageView commodityImg;
    //商品名称
    private TextView commodityName;
    //兑换星数
    private TextView commodityStars;
    //优惠
    private TextView discount;
    //邮费
    private TextView freight;
    //地址
    private TextView address;
    //商品介绍
    private TextView commodityDetail;
    //购物车
    private View shoppingCart;
    //加入购物车
    private Button addShoppingCart;
    //立即兑换
    private Button exchangeButton;
    private TitleBar titleBar;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    //是否登录
    private boolean isLogin;
    //用户
    private User mUser;
    private Commodity mCommodity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);
        initView();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_commodity_detail);
        commodityImg = findViewById(R.id.commodity_detail_img);
        commodityName = findViewById(R.id.commodity_detail_name);
        commodityStars = findViewById(R.id.commodity_detail_stars);
        commodityDetail = findViewById(R.id.commodity_detail_introduction);
        discount = findViewById(R.id.discount_text);
        freight = findViewById(R.id.freight_type);
        address = findViewById(R.id.address_detail);
        shoppingCart = findViewById(R.id.shopping_cart);
        addShoppingCart = findViewById(R.id.add_shopping_cart);
        exchangeButton = findViewById(R.id.exchange_commodity);
        discount.setOnClickListener(this);
        freight.setOnClickListener(this);
        address.setOnClickListener(this);
        shoppingCart.setOnClickListener(this);
        addShoppingCart.setOnClickListener(this);
        exchangeButton.setOnClickListener(this);
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
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mCommodity = (Commodity) bundle.get("commodity");
        Glide.with(this).load(mCommodity.getImageId()).into(commodityImg);
        commodityName.setText(mCommodity.getCommodityName());
        commodityStars.setText("★ "+mCommodity.getCommodityStars());
        commodityDetail.setText(mCommodity.getCommodityIntroduction());
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        isLogin = pref.getBoolean("logining", false);
        if (isLogin){
            String user_name = pref.getString("user_name","");
            String password = pref.getString("password","");
            UserDAO userDAO = new UserDAO(this);
            mUser = userDAO.query(user_name, password);
            address.setText(mUser.getAddress());
        }else {
            address.setText("请登录后重试...");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.discount_text:
//                Toast.makeText(this,"益起来公益", Toast.LENGTH_SHORT).show();
                break;

            case R.id.freight_type:
                Toast.makeText(this,"现在购买全场包邮哦", Toast.LENGTH_SHORT).show();
                break;

            case R.id.address_detail:
                if (isLogin){
                    //可跳转至修改地址界面
                    Toast.makeText(this,"配送至："+((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"请登录！", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.shopping_cart:
                if (isLogin){
                    Intent shoppingCartIntent = new Intent(CommodityDetailActivity.this, ShoppingCartActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mUser", mUser);
                    shoppingCartIntent.putExtras(bundle);
                    startActivity(shoppingCartIntent);
                }else {
                    Toast.makeText(this,"请登录！", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.add_shopping_cart:
                if (isLogin){
                    //可跳转至修改地址界面
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUserId(mUser.get_id());
                    shoppingCart.setCommodityId(mCommodity.get_id());
                    shoppingCart.setCommodityName(mCommodity.getCommodityName());
                    shoppingCart.setNum(1);
                    ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO(this);
                    shoppingCartDAO.add(shoppingCart);
                    Toast.makeText(this, "添加购物车成功！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"请登录！", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.exchange_commodity:
                if (isLogin){
                    //结算页面
                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setUser_id(mUser.get_id());
                    shoppingCartItem.setCommodityName(mCommodity.getCommodityName());
                    shoppingCartItem.setCommodityStars(mCommodity.getCommodityStars());
                    shoppingCartItem.setCommodity_id(mCommodity.get_id());
                    shoppingCartItem.setCommodityIntroduction(mCommodity.getCommodityIntroduction());
                    shoppingCartItem.setImageId(mCommodity.getImageId());
                    shoppingCartItem.setCount(1);
                    List<ShoppingCartItem> orderList = new ArrayList<>();
                    orderList.add(shoppingCartItem);
                    Intent intent = new Intent(CommodityDetailActivity.this, SettlementActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderList", (Serializable) orderList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"请登录！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
