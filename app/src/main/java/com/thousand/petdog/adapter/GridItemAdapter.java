package com.thousand.petdog.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thousand.petdog.R;
import com.thousand.petdog.bean.NavgationItem;

import java.util.List;

public class GridItemAdapter extends BaseQuickAdapter<NavgationItem,BaseViewHolder> {

    public GridItemAdapter(int layoutResId, @Nullable List<NavgationItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavgationItem item) {
        helper.setText(R.id.textview_grid_item_main,item.getNavgationName());
        Glide.with(mContext).load(item.getNavgationImg()).into((ImageView) helper.getView(R.id.img_grid_item_main));
    }
}
