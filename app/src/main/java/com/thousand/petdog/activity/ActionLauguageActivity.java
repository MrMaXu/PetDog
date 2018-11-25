package com.thousand.petdog.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.thousand.petdog.R;


/**
 *  Activity：行为语言
 *  描述：第4小块儿：宠物的行为语言，显示宠物狗常见行为动作和意义
 */
public class ActionLauguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_lauguage);
        //设置标题栏
        Toolbar tb_memday_index = findViewById(R.id.tb_action);
        tb_memday_index.setTitle("动作指导");
        setSupportActionBar(tb_memday_index);

        //返回三角
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
