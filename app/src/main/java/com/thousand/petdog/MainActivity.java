package com.thousand.petdog;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.thousand.petdog.fragement.FindFragment;
import com.thousand.petdog.fragement.IndexFragment;
import com.thousand.petdog.fragement.MyFragment;
import com.thousand.petdog.fragement.ShopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：主页
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.fragment_container)
    RelativeLayout fragmentContainer;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    ConstraintLayout container;
    //Fragment Object
    //Fragmengt  首页 发现 商城 我的  1234
    private IndexFragment indexFragment;
    private FindFragment findFragment;
    private ShopFragment shopFragment;
    private MyFragment myFragment;
    private FragmentManager fManager;

    private Fragment[] fragments;
    private int lastShowFragment = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0) {
                        switchFrament(lastShowFragment, 0);
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.navigation_classify:
                    if (lastShowFragment != 1) {
                        switchFrament(lastShowFragment, 1);
                        lastShowFragment = 1;
                    }
                    return true;
//                case R.id.navigation_shop:
//                    if (lastShowFragment != 2) {
//                        switchFrament(lastShowFragment, 2);
//                        lastShowFragment = 2;
//                    }
//                    return true;
                case R.id.navigation_tickets:
                    if (lastShowFragment != 2) {
                        switchFrament(lastShowFragment, 2);
                        lastShowFragment = 2;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fManager = getFragmentManager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
    }

    private void initFragments() {
        indexFragment = new IndexFragment();
        findFragment = new FindFragment();
        //shopFragment=new ShopFragment();
        myFragment = new MyFragment();
        fragments = new Fragment[]{indexFragment,findFragment,myFragment};
      //  fragments = new Fragment[]{indexFragment,findFragment,shopFragment,myFragment};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, indexFragment)
                .show(indexFragment)
                .commit();
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragment_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

}

