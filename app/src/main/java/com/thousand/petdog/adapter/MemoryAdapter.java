package com.thousand.petdog.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thousand.petdog.R;
import com.thousand.petdog.model.MemoryDay;
import com.thousand.petdog.utils.TimeUtils;

import java.util.Date;
import java.util.List;

import static com.thousand.petdog.utils.TimeUtils.DATE_DAY;
import static com.thousand.petdog.utils.TimeUtils.DATE_MONTH;
import static com.thousand.petdog.utils.TimeUtils.DATE_YEAR;
import static com.thousand.petdog.utils.TimeUtils.getTimeInterval;

public class MemoryAdapter extends BaseQuickAdapter<MemoryDay,BaseViewHolder> {
    public MemoryAdapter(int layoutResId, @Nullable List<MemoryDay> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemoryDay item) {
        long date = item.getDate();
        helper.setText(R.id.tv_big_day,TimeUtils.getDateString(date,DATE_YEAR));
        helper.setText(R.id.tv_big_year_month,TimeUtils.getDateString(date,DATE_MONTH));
        helper.setBackgroundColor(R.id.rl_date,item.getColor());
        helper.setText(R.id.item_memory_contnet,item.getContent());
        String interval = getTimeInterval(new Date(),new Date(item.getDate()));
        helper.setText(R.id.item_memory_days,interval);
    }
}
