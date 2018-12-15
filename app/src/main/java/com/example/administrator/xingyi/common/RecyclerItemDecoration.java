package com.example.administrator.xingyi.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ting on 2018/12/2.
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private int itemSpace;
    private int itemNum;

    /**
     *
     * @param itemSpace item间隔
     * @param itemNum 每行item的个数
     */
    public RecyclerItemDecoration(int itemSpace, int itemNum) {
        this.itemSpace = itemSpace;
        this.itemNum = itemNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = itemSpace;
        if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
            outRect.left = 0;
        } else {
            outRect.left = itemSpace;
        }

    }
}