package com.thousand.petdog.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.thousand.petdog.R;

/**
 * Activity：健康监测
 * 描述：第8小块儿：监测宠物的健康情况
 */
public class HealthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        Toolbar tb_my = findViewById(R.id.tb_my);
        setSupportActionBar(tb_my);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
