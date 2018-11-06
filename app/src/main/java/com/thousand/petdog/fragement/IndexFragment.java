package com.thousand.petdog.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thousand.petdog.R;
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
    private String content;
    private List<NavgationItem> mItemList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    View view;

    public IndexFragment() {
        super();
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
        gridLayoutManager = new GridLayoutManager(getActivity(),4);
    }

    private void initRecyclerView() {
        rvFgIndex.setLayoutManager(gridLayoutManager);
        GridItemAdapter gridItemAdapter = new GridItemAdapter(R.layout.item_grid_main,mItemList);
        gridItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        rvFgIndex.setAdapter(gridItemAdapter);
    }

    private void initList() {
        for (int i=0;i<8;i++){
            mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"测试"));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
