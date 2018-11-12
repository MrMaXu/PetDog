package com.thousand.petdog.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thousand.petdog.R;
import com.thousand.petdog.adapter.FinddogItemAdapter;
import com.thousand.petdog.adapter.FinddogNaviAdapter;
import com.thousand.petdog.bean.FinddogItem;
import com.thousand.petdog.util.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FindFragment extends Fragment {

    @BindView(R.id.filter_edit)
    ClearEditText filterEdit;
    @BindView(R.id.rv_findfragment)
    RecyclerView recyclerViewFindFragment;
    Unbinder unbinder;
    @BindView(R.id.rv_findfragment_info)
    RecyclerView recyclerFindfragmentInfo;
    private String content;
    private List<String> mStringlList = new ArrayList<>();
    private List<FinddogItem> mFinddogItemList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    public FindFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        mStringlList.add("寄养");
        mStringlList.add("配对");
        mStringlList.add("领养");
        mStringlList.add("分享");

        for (int j=0;j<5;j++){
            FinddogItem finddogItem = new FinddogItem();
            finddogItem.setUserName("lql");
            finddogItem.setUserImg(R.drawable.ic_search_black_24dp);
            finddogItem.setContent(getResources().getString(R.string.test_text));

            List<Integer> list = new ArrayList<>();
            list.add(R.drawable.p1);
            list.add(R.drawable.ic_star_black_24dp);
            list.add(R.drawable.ic_star_black_24dp);
            finddogItem.setContentImgList(list);

            mFinddogItemList.add(finddogItem);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_find, container, false);
        unbinder = ButterKnife.bind(this, view);

        gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerViewFindFragment.setLayoutManager(gridLayoutManager);
        FinddogNaviAdapter adapter = new FinddogNaviAdapter(getActivity(), mStringlList);
        recyclerViewFindFragment.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerFindfragmentInfo.setLayoutManager(linearLayoutManager);
        FinddogItemAdapter finddogItemAdapter = new FinddogItemAdapter(R.layout.item_finddog,mFinddogItemList);
        finddogItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();
            }
        });
        finddogItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                if (id == R.id.tv_dogitem_favoriate){
                    ToggleButton favoriateToogle = view.findViewById(R.id.tv_dogitem_favoriate);
                    favoriateToogle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b){
                                Toast.makeText(getActivity(),"已关注",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(),"取消关注",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else if (id == R.id.tv_dogitem_content){

                }
            }
        });
        recyclerFindfragmentInfo.setAdapter(finddogItemAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
