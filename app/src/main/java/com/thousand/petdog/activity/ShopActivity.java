package com.thousand.petdog.activity;

import android.app.Activity;
import android.os.Bundle;

import com.mob.MobSDK;
import com.mob.shop.datatype.entity.Order;
import com.mob.shop.gui.Callback;
import com.mob.shop.gui.ShopGUI;
import com.mob.shop.gui.pay.customizedpay.CustomizedPayListener;
import com.thousand.petdog.R;

/**
 * Activity：秘密
 * 描述：第2小块儿：记录宠物的日常照片
 */
public class ShopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);
        MobSDK.init(this);


        Callback callback = new Callback() {
            //跳转开发者应用的登录界面，等待用户进行登录操作，登录成功后调用MobSDK.setUser设置登录用户信息
            @Override
            public void login() {
                super.login();

            }

            // 跳转开发者应用的退出登录界面，等待用户进行退出操作，退出成功后调用MobSDK.clearUser清除用户信息
            @Override
            public void logout() {
                super.logout();
            }

            // 自有支付流程
            @Override
            public boolean pay(Order order, CustomizedPayListener listener) {
                return super.pay(order, listener);
            }
        };

        // 进入商城首页
        ShopGUI.showShopPage(callback);

    }

}
