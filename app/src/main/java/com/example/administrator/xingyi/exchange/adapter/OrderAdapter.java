package com.example.administrator.xingyi.exchange.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.dao.ShoppingCartDAO;
import com.example.administrator.xingyi.exchange.CommodityDetailActivity;
import com.example.administrator.xingyi.model.ShoppingCartItem;

import java.util.List;

/**
 * Created by ting on 2018/12/9.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<ShoppingCartItem> shoppingCartList;
    private Context context;

    public OrderAdapter(Context context, List<ShoppingCartItem> shoppingCartList) {
        this.context = context;
        this.shoppingCartList = shoppingCartList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        final ViewHolder holder;
        View view;
        if (viewGroup != null) {
            view = LayoutInflater.from(context).inflate(R.layout.order_item, viewGroup, false);
            holder = new OrderAdapter.ViewHolder(view);
            viewGroup.setTag(holder);
        } else {
            holder = (ViewHolder) viewGroup.getTag();
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ShoppingCartItem item = shoppingCartList.get(i);
        Glide.with(context).load(CommodityDetailActivity.COMMODITY_IMAGES[item.getCommodity_id()-1]).into(viewHolder.iv_show_pic);
//        holder.iv_show_pic.setImageResource(CommodityDetailActivity.COMMODITY_IMAGES[item.getCommodity_id()-1]);
        viewHolder.tv_commodity_name.setText(item.getCommodityName());
        viewHolder.tv_commodity_introduction.setText("详情:" + item.getCommodityIntroduction());
        viewHolder.tv_price.setText("★:" + item.getCommodityStars());
        viewHolder.tv_show_num.setText(item.getCount() + "");
        viewHolder.tv_num.setText("X" + item.getCount());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return shoppingCartList == null ? 0 : shoppingCartList.size();
    }


    //初始化控件  
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_chose;
        ImageView iv_show_pic, iv_sub, iv_add;
        TextView tv_commodity_name, tv_commodity_introduction, tv_price, tv_num, tv_show_num;
        RelativeLayout rl_edit;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_show_pic = (ImageView) itemView.findViewById(R.id.iv_show_pic);
            iv_sub = (ImageView) itemView.findViewById(R.id.iv_sub);
            iv_add = (ImageView) itemView.findViewById(R.id.iv_add);

            tv_commodity_name = (TextView) itemView.findViewById(R.id.tv_commodity_name);

            tv_commodity_introduction = (TextView) itemView.findViewById(R.id.tv_commodity_introduction);
            tv_price = (TextView) itemView.findViewById(R.id.tv_commodity_stars);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            tv_show_num = (TextView) itemView.findViewById(R.id.tv_show_num);
            rl_edit = (RelativeLayout) itemView.findViewById(R.id.rl_edit);

        }

    }
}
