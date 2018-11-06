package com.thousand.petdog.activity;

import android.app.Activity;

import android.os.Bundle;

import com.thousand.petdog.R;
/**
 *  Activity：撩狗
 *  描述：第3小块儿：显示宠物狗的照片动作和解释，点击播放声音
 */
public class LiaogouActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liaogou);
    }
}
