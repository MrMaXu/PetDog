package com.thousand.petdog.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thousand.petdog.R;
import com.thousand.petdog.activity.ActionLauguageActivity;
import com.thousand.petdog.activity.BaiduLocateActivity;
import com.thousand.petdog.activity.HealthActivity;
import com.thousand.petdog.activity.LiaogouActivity;
import com.thousand.petdog.activity.MapActivity;
import com.thousand.petdog.activity.MemoryDayActivity;
import com.thousand.petdog.activity.ShopActivity;
import com.thousand.petdog.activity.SickActivity;
import com.thousand.petdog.activity.TrainArticleActivity;
import com.thousand.petdog.adapter.GridItemAdapter;
import com.thousand.petdog.bean.NavgationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class IndexFragment extends Fragment {

    @BindView(R.id.rv_fg_index)
    RecyclerView rvFgIndex;

    Unbinder unbinder;
    @BindView(R.id.index_fragment_toolbar)
    Toolbar indexFragmentToolbar;
    private String content;
    private List<NavgationItem> mItemList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    View view;

    public IndexFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_index, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initList();         //初始化数据
        initRecyclerView();
        return view;
    }

    private void initView() {
        AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(indexFragmentToolbar);
        indexFragmentToolbar.setTitle("");
        gridLayoutManager = new GridLayoutManager(getActivity(), 4);

    }

    private void initRecyclerView() {
        rvFgIndex.setLayoutManager(gridLayoutManager);
        GridItemAdapter gridItemAdapter = new GridItemAdapter(R.layout.item_grid_main, mItemList);
        gridItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), MemoryDayActivity.class));
                        break;
                    case 1:
                       // Toast.makeText(getActivity(),"该功能正在开发中",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), ShopActivity.class));

                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), LiaogouActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), ActionLauguageActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), TrainArticleActivity.class));
                        break;
                    case 5:
                       // startActivity(new Intent(getActivity(), BaiduLocateActivity.class));
                         startActivity(new Intent(getActivity(), MapActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), SickActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(), HealthActivity.class));
                        break;
                }
            }
        });
        rvFgIndex.setAdapter(gridItemAdapter);
    }

    private void initList() {
        mItemList.add(new NavgationItem(R.drawable.d12, "纪念日"));
        mItemList.add(new NavgationItem(R.drawable.d11, "商城"));
        mItemList.add(new NavgationItem(R.drawable.d13, "撩汪"));
        mItemList.add(new NavgationItem(R.drawable.d14, "动作"));
        mItemList.add(new NavgationItem(R.drawable.d15, "训练"));
        mItemList.add(new NavgationItem(R.drawable.d16, "地图"));
        mItemList.add(new NavgationItem(R.drawable.d17, "病种百科"));
        mItemList.add(new NavgationItem(R.drawable.d18, "健康监控"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.indexfragment_menu, menu);
    }
}
