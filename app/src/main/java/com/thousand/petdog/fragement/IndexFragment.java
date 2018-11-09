package com.thousand.petdog.fragement;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thousand.petdog.R;
import com.thousand.petdog.activity.ActionLauguageActivity;
import com.thousand.petdog.activity.LiaogouActivity;
import com.thousand.petdog.activity.MapActivity;
import com.thousand.petdog.activity.MemoryDayActivity;
import com.thousand.petdog.activity.SecretActivity;
import com.thousand.petdog.activity.SickActivity;
import com.thousand.petdog.activity.TrainArticleActivity;
import com.thousand.petdog.adapter.GridItemAdapter;
import com.thousand.petdog.bean.HealthDateItem;
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
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(),MemoryDayActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(),SecretActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(),LiaogouActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(),ActionLauguageActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(),TrainArticleActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(),MapActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(),SickActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(),HealthDateItem.class));
                        break;
                }
            }
        });
        rvFgIndex.setAdapter(gridItemAdapter);
    }

    private void initList() {
        mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"纪念日"));
        mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"秘密"));
        mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"撩汪"));
        mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"动作"));
        mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"训练"));
        mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"地图"));
        mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"病种百科"));
        mItemList.add(new NavgationItem(R.drawable.ic_launcher_background,"健康监控"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
