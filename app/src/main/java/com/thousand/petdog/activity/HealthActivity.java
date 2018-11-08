package com.thousand.petdog.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.thousand.petdog.R;
import com.thousand.petdog.activity.sub_activity.AddMemoryActivity;
import com.thousand.petdog.adapter.ListHealthAdapter;
import com.thousand.petdog.bean.HealthDateItem;
import com.thousand.petdog.model.Dog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity：健康监测
 * 描述：第8小块儿：监测宠物的健康情况
 */
public class HealthActivity extends AppCompatActivity {
    @BindView(R.id.dog_height)
    TextView dog_height;
    @BindView(R.id.dog_weight)
    TextView dog_weight;
    @BindView(R.id.dog_BMI)
    TextView dog_BMI;
    @BindView(R.id.dog_temperature)
    TextView dog_temperature;
    @BindView(R.id.dog_inputData)
    Button dog_inputData;
    @BindView(R.id.dog_inputHeight)
    EditText dog_inputHeight;
    @BindView(R.id.dog_inputWeight)
    EditText dog_inputWeight;
    @BindView(R.id.dog_inputTemperature)
    EditText dog_inputTemperature;
    private ArrayList<Dog> list;
    private ListHealthAdapter listHealthAdapter;
    @BindView(R.id.rcv_health)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        ButterKnife.bind(this);

        Toolbar tb_health = findViewById(R.id.tb_health);
        tb_health.setTitle("健康监测");
        setSupportActionBar(tb_health);

        //返回三角
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<Dog>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listHealthAdapter = new ListHealthAdapter(R.layout.item_list_health, list);
        recyclerView.setAdapter(listHealthAdapter);
        listHealthAdapter.openLoadAnimation();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_memory, menu);
        return super.onCreateOptionsMenu(menu);
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

    @OnClick(R.id.dog_inputData)
    public void setDog_inputData() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(HealthActivity.this);
        builder.setView(R.layout.diglog_health);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @OnClick({R.id.dog_inputData, R.id.dog_inputHeight, R.id.dog_inputWeight, R.id.dog_inputTemperature, R.id.dogData_submit, R.id.dogData_cancle})
    public void setDogData(View v) {
        switch (v.getId()) {
            case R.id.dog_inputData:
                final AlertDialog.Builder builder = new AlertDialog.Builder(HealthActivity.this);
                builder.setView(R.layout.diglog_health);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
//          case R.id.dog_inputHeight:
//                break;
//            case R.id.dog_inputWeight:
//                break;
//            case R.id.dog_inputTemperature:
//                break;
            case R.id.dogData_submit:
                String str_dog_inputHeight = dog_inputHeight.getText().toString();
                String str_dog_inputWeight = dog_inputWeight.getText().toString();
                String str_dog_inputTemperature = dog_inputTemperature.getText().toString();
                dog_height.setText(str_dog_inputHeight);
                dog_weight.setText(str_dog_inputWeight);
                dog_temperature.setText(str_dog_inputTemperature);
                getTime();
                //计算BMI
                Dog dogData = new Dog();
                dogData.setDog_height(formatString(str_dog_inputHeight));
                dogData.setDog_weight(formatString(str_dog_inputWeight));
                dogData.setDog_temperature(formatString(str_dog_inputTemperature));
                dogData.save();

                break;
            case R.id.dogData_cancle:
                break;

        }
    }

    //将String数据格式化为float，且保留一位小数
    public Float formatString(String s) {
        float f = Float.parseFloat(s);
        BigDecimal bd = new BigDecimal((double) f);
        bd = bd.setScale(2, 4);
        f = bd.floatValue();
        return f;
    }

    public void getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());
        String sTime = formatter.format(curDate);
        for (int i = 0; i < 10; i++) {
            Dog dog = new Dog(sTime);
            list.add(dog);
        }

    }
}
