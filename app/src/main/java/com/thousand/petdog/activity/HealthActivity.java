package com.thousand.petdog.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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
public class HealthActivity extends AppCompatActivity implements View.OnClickListener {
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

        dog_inputData.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.dog_inputData){
            final AlertDialog.Builder builder = new AlertDialog.Builder(HealthActivity.this);
            View dialogView = LayoutInflater.from(this).inflate(R.layout.diglog_health,null);
            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();


            //身高、体重、体温
            final EditText dog_inputHeight =(EditText) dialogView.findViewById(R.id.dog_inputHeight);
            final EditText dog_inputWeight =(EditText) dialogView.findViewById(R.id.dog_inputWeight);
            final EditText dog_inputTemperature =(EditText) dialogView.findViewById(R.id.dog_inputTemperature);
            //对话框确定、取消按钮
            Button dogDataSubmit = (Button) dialogView.findViewById(R.id.dogData_submit);
            Button dogDataCancle = (Button) dialogView.findViewById(R.id.dogData_cancle);
            //确定按钮监听
            dogDataSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //计算
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
                    alertDialog.dismiss();
                }
            });
            dogDataCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }
}
