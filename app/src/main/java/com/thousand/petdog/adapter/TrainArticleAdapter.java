package com.thousand.petdog.adapter;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thousand.petdog.R;
import com.thousand.petdog.model.Article;

import java.util.List;

/**
 * 创建人：Mir.Ma
 * 时间：2018/11/9 16:11
 * 描述：
 */
public class TrainArticleAdapter extends BaseQuickAdapter<Article,BaseViewHolder> {
    public TrainArticleAdapter(int layoutResId, @Nullable List<Article> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.tv_article_title,item.getArticleTitle());

    }
}
