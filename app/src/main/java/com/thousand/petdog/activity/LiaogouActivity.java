package com.thousand.petdog.activity;

import android.app.Activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.thousand.petdog.R;
import com.thousand.petdog.adapter.RecyclerGridViewAdapter;

/**
 *  Activity：撩狗
 *  描述：第3小块儿：显示宠物狗的照片动作和解释，点击播放声音
 */
public class LiaogouActivity extends Activity {
private View v;
private RecyclerView recyclerView;
private String[] data={};
private int[] imgdata={};
private RecyclerGridViewAdapter recyclerGridViewAdapter;
private GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liaogou);
        initView();
    }
    public  void initView(){
        recyclerView=findViewById(R.id.recyclerView);
        gridLayoutManager= new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false);
    }
}
