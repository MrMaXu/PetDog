package com.thousand.petdog.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thousand.petdog.R;

import butterknife.ButterKnife;

/**
 * Activity：纪念日
 * 描述：第1小块儿：显示和宠物狗仔一起的天数
 */
public class MemoryDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_day_index);
        ButterKnife.bind(this);
        //设置标题栏
        Toolbar tb_memday_index = findViewById(R.id.tb_memday_index);
        setSupportActionBar(tb_memday_index);

        //返回三角
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
         //返回上级Activity
            case  android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
