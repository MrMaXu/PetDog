package com.thousand.petdog.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thousand.petdog.R;
import com.thousand.petdog.adapter.TrainArticleAdapter;
import com.thousand.petdog.model.Article;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Activity：训练
 * 描述：第5小块儿：训练宠物的文章
 */
public class TrainArticleActivity extends AppCompatActivity {
    TrainArticleAdapter trainArticleAdapter;
    RecyclerView recyclerView;
    Article article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_article);
        ButterKnife.bind(this);
        //设置标题栏
        Toolbar tb_memday_index = findViewById(R.id.tb_memday_index);
        tb_memday_index.setTitle("训练宝典");
        setSupportActionBar(tb_memday_index);

        //返回三角
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

        initData();

        trainArticleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

             //   Bundle bundle = new Bundle();
            //    bundle.putString("ArticleTitle",mList.get(position));
            }
        });
    }

    private List<Article> mList = new ArrayList<>();

    public void initView() {
        trainArticleAdapter = new TrainArticleAdapter(R.layout.item_trainarticle, mList);
        recyclerView = findViewById(R.id.rcv_train_article);
        recyclerView.setLayoutManager(new LinearLayoutManager(TrainArticleActivity.this));
        recyclerView.setAdapter(trainArticleAdapter);





    }

    public void initData() {

        for (int i = 0; i < 10; i++) {
             article = new Article("如何让狗狗变得乖巧可爱？");
            mList.add(article);
        }
        mList = LitePal.findAll(Article.class);

    }
}
