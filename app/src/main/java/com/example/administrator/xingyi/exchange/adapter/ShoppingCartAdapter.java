package com.example.administrator.xingyi.exchange.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * Created by ting on 2018/12/6.
 */

public class ShoppingCartAdapter extends BaseAdapter {

    private boolean isShow = true;//是否显示编辑/完成  
    private List<ShoppingCartItem> shoppingCartList;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private Context context;
    private ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartAdapter(Context context) {
        this.context = context;
        shoppingCartDAO = new ShoppingCartDAO(context);
    }

    public void setShoppingCartList(List<ShoppingCartItem> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
        notifyDataSetChanged();
    }


    //单选接口
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }


    //改变商品数量接口

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getCount() {
        return shoppingCartList == null ? 0 : shoppingCartList.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingCartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    /* 
      是否显示可编辑 
      
      @param flag 
     */
    public void isShow(boolean flag) {
        isShow = flag;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ShoppingCartItem item = shoppingCartList.get(position);
        Glide.with(context).load(CommodityDetailActivity.COMMODITY_IMAGES[item.getCommodity_id()-1]).into(holder.iv_show_pic);
//        holder.iv_show_pic.setImageResource(CommodityDetailActivity.COMMODITY_IMAGES[item.getCommodity_id()-1]);
        holder.tv_commodity_name.setText(item.getCommodityName());
        holder.tv_commodity_introduction.setText("详情:" + item.getCommodityIntroduction());
        holder.tv_price.setText("★:" + item.getCommodityStars());
        holder.ck_chose.setChecked(item.isChoosed());
        holder.tv_show_num.setText(item.getCount() + "");
        holder.tv_num.setText("X" + item.getCount());

        //单选框按钮  
        holder.ck_chose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口  
                    }
                }
        );

        //增加按钮  
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露增加接口  
            }
        });

        //删减按钮  
        holder.iv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露删减接口  
            }
        });

        //判断是否在编辑状态下  
        if (isShow) {
//            holder.tv_commodity_name.setVisibility(View.VISIBLE);
            holder.rl_edit.setVisibility(View.GONE);
        } else {
//            holder.tv_commodity_name.setVisibility(View.GONE);
            holder.rl_edit.setVisibility(View.VISIBLE);
        }

        return convertView;
    }


    //初始化控件  
    class ViewHolder {
        ImageView iv_chose;
        ImageView iv_show_pic, iv_sub, iv_add;
        TextView tv_commodity_name, tv_commodity_introduction, tv_price, tv_num, tv_show_num;
        CheckBox ck_chose;
        RelativeLayout rl_edit;

        public ViewHolder(View itemView) {
            ck_chose = (CheckBox) itemView.findViewById(R.id.ck_chose);
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

    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    //改变数量的接口
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);


        /**
         * 删减操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的w
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

        //删除子item
        void childDelete(int position);
    }

}

