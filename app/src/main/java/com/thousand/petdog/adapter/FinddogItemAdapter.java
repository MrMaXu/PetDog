package com.thousand.petdog.adapter;

import android.support.annotation.Nullable;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thousand.petdog.R;
import com.thousand.petdog.bean.FinddogItem;

import java.util.List;

public class FinddogItemAdapter extends BaseQuickAdapter<FinddogItem,BaseViewHolder> {

    public FinddogItemAdapter(int layoutResId, @Nullable List<FinddogItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinddogItem item) {
        Glide.with(mContext).load(item.getUserImg()).into((ImageView) helper.getView(R.id.img_dogitem_userimg));
        helper.setText(R.id.tv_dogitem_username,item.getUserName());
        helper.setText(R.id.tv_dogitem_content,item.getContent());
        //itemContent图片
        List<Integer> contentImgList = item.getContentImgList();
        GridLayout gridLayout = helper.getView(R.id.gridlayout_content_imgs);
        for (int i=0;i<contentImgList.size();i++){
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(64,64);
            imageView.setLayoutParams(layoutParams);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(contentImgList.get(i)).into(imageView);
            gridLayout.addView(imageView);
        }
        helper.addOnClickListener(R.id.tv_dogitem_favoriate);
        helper.addOnClickListener(R.id.tv_dogitem_content);

    }
}
