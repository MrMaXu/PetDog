package com.thousand.petdog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thousand.petdog.R;
import com.thousand.petdog.activity.sub_activity.AddMemoryActivity;
import com.thousand.petdog.adapter.MemoryAdapter;
import com.thousand.petdog.model.MemoryDay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity：纪念日
 * 描述：第1小块儿：显示和宠物狗仔一起的天数
 */
public class MemoryDayActivity extends AppCompatActivity {

    public static final String MEMORY_ITEM = "memory_item";

    @BindView(R.id.rv_memory)
    RecyclerView rvMemory;
    private List<MemoryDay> mMemoryDayList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_day_add);
        ButterKnife.bind(this);
        //设置标题栏
        Toolbar tb_memday_index = findViewById(R.id.tb_memday_index);
        tb_memday_index.setTitle("纪念日");
        setSupportActionBar(tb_memday_index);

        //返回三角
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initListData();
        mLinearLayoutManager = new LinearLayoutManager(this);
        rvMemory.setLayoutManager(mLinearLayoutManager);
        MemoryAdapter memoryAdapter = new MemoryAdapter(R.layout.item_linear_img_text,mMemoryDayList);
        memoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MemoryDayActivity.this,AddMemoryActivity.class);
                intent.putExtra("memory_item",mMemoryDayList.get(position));
                startActivity(intent);
            }
        });
        rvMemory.setAdapter(memoryAdapter);
    }

    private void initListData() {
        for (int i = 0; i < 10; i++) {
            MemoryDay memoryDay = new MemoryDay("test", "测试测试", new Date().getTime());
            memoryDay.setColor(R.color.colorPrimary);
            mMemoryDayList.add(memoryDay);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memory, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回上级Activity
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_memory_add:
                startActivity(new Intent(MemoryDayActivity.this, AddMemoryActivity.class));
                break;
            case R.id.menu_memory_sort:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
