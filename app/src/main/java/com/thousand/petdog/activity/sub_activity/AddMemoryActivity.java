package com.thousand.petdog.activity.sub_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thousand.petdog.R;
import com.thousand.petdog.model.MemoryDay;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.thousand.petdog.activity.MemoryDayActivity.MEMORY_ITEM;

public class AddMemoryActivity extends AppCompatActivity {

    @BindView(R.id.tb_memday_add)
    Toolbar tbMemdayAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memory);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            MemoryDay memoryDay = (MemoryDay) getIntent().getSerializableExtra(MEMORY_ITEM);
        }
        tbMemdayAdd.setTitle(getResources().getString(R.string.memory_add_add));
        setSupportActionBar(tbMemdayAdd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_memory,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //返回上级Activity
            case  android.R.id.home:
                finish();
                break;
            case R.id.menu_add_memory_finish:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
