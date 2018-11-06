package com.thousand.petdog.activity;

import android.app.Activity;
import android.os.Bundle;

import com.thousand.petdog.R;
/**
 *  Activity：秘密
 *  描述：第2小块儿：记录宠物的日常照片
 */
public class SecretActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);
    }
}
