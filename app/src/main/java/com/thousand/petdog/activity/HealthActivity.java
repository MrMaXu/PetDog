package com.thousand.petdog.activity;

import android.app.Activity;
import android.os.Bundle;

import com.thousand.petdog.R;

/**
 *  Activity：健康监测
 *  描述：第8小块儿：监测宠物的健康情况
 */
public class HealthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
    }
}
