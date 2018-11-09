package com.thousand.petdog.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thousand.petdog.R;
import com.thousand.petdog.activity.sub_activity.AddMemoryActivity;
import com.thousand.petdog.adapter.LiaogouItemAdapter;
import com.thousand.petdog.adapter.RecyclerGridViewAdapter;
import com.thousand.petdog.bean.LiaogouItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity：撩狗
 * 描述：第3小块儿：显示宠物狗的照片动作和解释，点击播放声音
 */
public class LiaogouActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_liaogou)
    Toolbar toolbarLiaogou;
    @BindView(R.id.rv_liaogou)
    RecyclerView recyclerViewLiaogou;

    private List<LiaogouItem> mLiaogouList = new ArrayList<>();

    private View v;
    private String[] data = {};
    private int[] imgdata = {};
    private RecyclerGridViewAdapter recyclerGridViewAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liaogou);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            LiaogouItem liaogouItem = new LiaogouItem(R.drawable.p1,"叫一个");
            mLiaogouList.add(liaogouItem);
        }
    }

    public void initView() {
        toolbarLiaogou.setTitle("撩狗神器");
        setSupportActionBar(toolbarLiaogou);
        //返回三角
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(this,3);
        recyclerViewLiaogou.setLayoutManager(gridLayoutManager);
        LiaogouItemAdapter liaogouItemAdapter = new LiaogouItemAdapter(R.layout.item_dog_voice,mLiaogouList);
        liaogouItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RelativeLayout relativeLayout = view.findViewById(R.id.rl_playvoice);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });
        recyclerViewLiaogou.setAdapter(liaogouItemAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回上级Activity
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
