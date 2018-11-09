package com.thousand.petdog.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thousand.petdog.R;
import com.thousand.petdog.bean.LiaogouItem;

import java.util.List;

public class LiaogouItemAdapter extends BaseQuickAdapter<LiaogouItem,BaseViewHolder> {

    public LiaogouItemAdapter(int layoutResId, @Nullable List<LiaogouItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiaogouItem liaogouItem) {
        helper.setText(R.id.tv_dogvoice_name,liaogouItem.getVoiceName());
        Glide.with(mContext).load(liaogouItem.getImg()).into((ImageView) helper.getView(R.id.img_dogvoice));
    }
}
