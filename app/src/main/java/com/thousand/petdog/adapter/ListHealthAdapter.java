package com.thousand.petdog.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thousand.petdog.R;
import com.thousand.petdog.bean.HealthDateItem;
import com.thousand.petdog.model.Dog;

import java.util.Date;
import java.util.List;

/**
 * 创建人：Mir.Ma
 * 时间：2018/11/8 20:31
 * 描述：
 */
public class ListHealthAdapter extends BaseQuickAdapter<Dog,BaseViewHolder> {
    public ListHealthAdapter(int layoutResId, @Nullable List<Dog> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Dog item) {
        helper.setText(R.id.item_health,item.getDog_healthDate());
    }
}
