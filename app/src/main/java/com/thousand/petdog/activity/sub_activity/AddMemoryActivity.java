package com.thousand.petdog.activity.sub_activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.thousand.petdog.R;
import com.thousand.petdog.model.MemoryDay;
import com.thousand.petdog.util.TimeUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.thousand.petdog.activity.MemoryDayActivity.MEMORY_DATE;
import static com.thousand.petdog.util.TimeUtils.DATE_DAY;

public class AddMemoryActivity extends AppCompatActivity implements OnDateSetListener {

    @BindView(R.id.tb_memday_add)
    Toolbar tbMemdayAdd;
    @BindView(R.id.ll_background)
    LinearLayout llBackground;
    @BindView(R.id.et_memory_content)
    EditText etMemoryContent;
    @BindView(R.id.tv_memory_time)
    TextView tvMemoryTime;

    private TimePickerDialog mDialogYearMonthDay;
    private MemoryDay mMemoryDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memory);
        ButterKnife.bind(this);

        mMemoryDay = new MemoryDay();
        if (getIntent() != null) {
            long date = getIntent().getLongExtra(MEMORY_DATE, new Date().getTime());
        }
        tbMemdayAdd.setTitle(getResources().getString(R.string.memory_add_add));
        setSupportActionBar(tbMemdayAdd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //实例化时间选择器
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();

        //选择时间
        tvMemoryTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogYearMonthDay.show(getSupportFragmentManager(),"year_month_day");
            }
        });

        //选择颜色
        llBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialogBuilder
                        .with(AddMemoryActivity.this)
                        .setTitle("Choose color")
                        .initialColor(getResources().getColor(R.color.colorPrimary))
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {

                            }
                        })
                        .setPositiveButton("确定", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                mMemoryDay.setColor(selectedColor);
                                llBackground.setBackgroundColor(selectedColor);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .build()
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_memory, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回上级Activity
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_add_memory_finish:
                String content = etMemoryContent.getText().toString();
                mMemoryDay.setContent(content);
                if (mMemoryDay.save()){
                    Toast.makeText(AddMemoryActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //时间选择回调
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String text = TimeUtils.getDateString(millseconds,DATE_DAY);
        mMemoryDay.setDate(millseconds);
        tvMemoryTime.setText(text);
    }
}
