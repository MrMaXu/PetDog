package com.thousand.petdog.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thousand.petdog.R;
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
    //直接创建，不需要设置setDataSource

    private List<LiaogouItem> mLiaogouList = new ArrayList<>();

    private View v;
    private String[] data = {};
    private int[] imgdata = {};
    private RecyclerGridViewAdapter recyclerGridViewAdapter;
    private GridLayoutManager gridLayoutManager;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liaogou);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        LiaogouItem d1 = new LiaogouItem(R.drawable.p1,"小奶狗",R.raw.naigou);
        LiaogouItem d2 = new LiaogouItem(R.drawable.p1,"吃狗粮",R.raw.r1);
        LiaogouItem d3 = new LiaogouItem(R.drawable.p1,"狗快速喘气",R.raw.r2);
        LiaogouItem d4 = new LiaogouItem(R.drawable.p1,"狗惨叫",R.raw.r3);
        LiaogouItem d5 = new LiaogouItem(R.drawable.p1,"狂吠",R.raw.r4);
        LiaogouItem d6 = new LiaogouItem(R.drawable.p1,"狗喝水",R.raw.r5);
        LiaogouItem d7 = new LiaogouItem(R.drawable.p1,"大声叫",R.raw.r6);
        LiaogouItem d8 = new LiaogouItem(R.drawable.p1,"遇见陌生人",R.raw.r8);
        LiaogouItem d9 = new LiaogouItem(R.drawable.p1,"大声叫",R.raw.r9);
        LiaogouItem d10 = new LiaogouItem(R.drawable.p1,"老狗呜声",R.raw.r10);
        LiaogouItem d11 = new LiaogouItem(R.drawable.p1,"二哈叫",R.raw.r12);
        mLiaogouList.add(d1);
        mLiaogouList.add(d2);
        mLiaogouList.add(d3);
        mLiaogouList.add(d4);
        mLiaogouList.add(d5);
        mLiaogouList.add(d6);
        mLiaogouList.add(d7);
        mLiaogouList.add(d8);
        mLiaogouList.add(d9);
        mLiaogouList.add(d10);
        mLiaogouList.add(d11);
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
                final RelativeLayout relativeLayout = view.findViewById(R.id.rl_playvoice);
                relativeLayout.setVisibility(View.VISIBLE);
                //创建播放器
                mMediaPlayer=MediaPlayer.create(LiaogouActivity.this, mLiaogouList.get(position).getGoujiao());
                if (mMediaPlayer!=null)
                    mMediaPlayer.start();    //开始播放
                //播放器监听器
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        relativeLayout.setVisibility(View.GONE);    //播放完后隐藏视图
                        mMediaPlayer.release();    //释放播放器
                    }
                });
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
