package com.thousand.petdog;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.mob.MobSDK;

import org.litepal.LitePal;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
        // 初始化LitePal数据库
        LitePal.initialize(this);
        MultiDex.install(this);
    }
}
