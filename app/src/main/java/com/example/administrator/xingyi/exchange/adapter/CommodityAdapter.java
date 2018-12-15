package com.example.administrator.xingyi.exchange.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.xingyi.R;
import com.example.administrator.xingyi.exchange.CommodityDetailActivity;
import com.example.administrator.xingyi.model.Commodity;

import java.util.List;

/**
 * Created by ting on 2018/12/2.
 */

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder> {
    private Context mContext;
    private List<Commodity> commodities;

    public CommodityAdapter(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        if (mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.commodity_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Commodity commodity = commodities.get(position);
                Intent intent = new Intent(mContext, CommodityDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("commodity", commodity);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Commodity commodity = commodities.get(i);
        RequestOptions options = new RequestOptions()
                .override(500, 500);
        Glide.with(mContext).load(commodity.getImageId()).apply(options).into(viewHolder.commodityImage);
        viewHolder.commodityName.setText(commodity.getCommodityName());
        viewHolder.commodityIntroduction.setText(commodity.getCommodityIntroduction());
        viewHolder.commodityStars.setText(String .valueOf(commodity.getCommodityStars()));
    }

    @Override
    public int getItemCount() {
        return commodities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView commodityImage;
        TextView commodityName;
        TextView commodityIntroduction;
        TextView commodityStars;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            commodityImage = itemView.findViewById(R.id.commodity_img);
            commodityName = itemView.findViewById(R.id.commodity_name);
            commodityIntroduction = itemView.findViewById(R.id.commodity_introduction);
            commodityStars = itemView.findViewById(R.id.commodity_stars);
        }
    }
}
