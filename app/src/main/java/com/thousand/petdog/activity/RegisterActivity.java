package com.thousand.petdog.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;
import com.thousand.petdog.MainActivity;
import com.thousand.petdog.R;
import com.thousand.petdog.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends Activity {
    @BindView(R.id.verCode)
    EditText verCode;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.sendSMS)
    Button sendSMS;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.second_password)
    EditText second_password;
    int i = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Context context = getApplicationContext();
// 启动短信验证sdk
        //初始化

        MobSDK.init(this);

        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eventHandler); // 注册回调监听接口
    }


    @OnClick({R.id.sendSMS, R.id.register})
    public void onClickSMS(View v) {
        String phoneNums = phone.getText().toString();
        switch (v.getId()) {
            case R.id.sendSMS:
                if (!judgePhoneNums(phoneNums)) {// 判断输入号码是否正确
                    return;
                }
                SMSSDK.getVerificationCode("86", phoneNums); // 调用sdk发送短信验证
                sendSMS.setClickable(false);// 设置按钮不可点击 显示倒计时
                sendSMS.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (i = 30; i > 0; i--) {

                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);// 线程休眠实现读秒功能
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);// 在30秒后重新显示为获取验证码
                    }
                }).start();
                break;

            case R.id.register:
                SMSSDK.submitVerificationCode("86", phoneNums, verCode.getText()
                        .toString());
                createProgressBar();
                break;
        }
    }

    /**
     *
     */
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                sendSMS.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                sendSMS.setText("获取验证码");
                sendSMS.setClickable(true); // 设置可点击
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
                        //验证成功，保存手机号，密码
                        User user = new User();
                        user.setPhone(Integer.parseInt(phone.getText().toString()));
                        user.setPassword(password.getText().toString());
                        user.save();
                        // 保存后跳转主界面
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        Log.e("注册", "成功！");
                        finish();// 成功跳转之后销毁当前页面

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "验证码已经发送",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };

    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11) && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
        return false;
    }

    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    //判断是否是手机号
    public static boolean isMobileNO(String mobileNums) {
        String telRegex = "[1][3456789]\\d{9}";// "[1]"代表第1位为数字1，"[3456789]"代表第二位可以为3、4、5、6、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    private void createProgressBar() {
        FrameLayout layout = (FrameLayout) findViewById(android.R.id.content);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        ProgressBar mProBar = new ProgressBar(this);
        mProBar.setLayoutParams(layoutParams);
        mProBar.setVisibility(View.VISIBLE);
        layout.addView(mProBar);
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }


}
