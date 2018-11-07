package com.thousand.petdog.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;

import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.thousand.petdog.R;
import com.thousand.petdog.adapter.SearchAddressAdapter;
import com.thousand.petdog.bean.AddressBean;


import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity：地图
 * 描述：第6小块儿：查看宠物店地图
 */
public class MapActivity extends Activity implements PoiSearch.OnPoiSearchListener, Inputtips.InputtipsListener, View.OnClickListener {
    private Context context;
    private MapView mMapView = null;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private InputtipsQuery inputquery;
    private Inputtips inputtips;
    private PoiSearch.Query query;
    private RelativeLayout rl_finish;
    private ListView lv_address;
    private PoiSearch poiSearch;
    private SearchAddressAdapter adapter;
    private ArrayList<AddressBean> data = new ArrayList<AddressBean>();
    int page = 0;
    //    @BindView(R.id.et_search)
//    EditText et_search;
//    @BindView(R.id.btn_search)
//    Button btn_search;
    private EditText et_search;
    private Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        context = this;
        initView();
        setOnClick();
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //放大18级
        mMapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(18));
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。

        myLocationStyle = new MyLocationStyle();
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //默认定位按钮是否显示
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
        //实例化UiSettings类对象
        mUiSettings = aMap.getUiSettings();
        //指南针
        mUiSettings.setCompassEnabled(true);
        //缩放手势
        mUiSettings.setZoomGesturesEnabled(true);
        //滑动手势
        mUiSettings.setScrollGesturesEnabled(true);
        //通过aMap对象设置定位数据源的监听
//          aMap.setLocationSource(this);
        //显示默认的定位按钮
        //   mUiSettings.setMyLocationButtonEnabled(true);
        // 可触发定位并显示当前位置
        //   aMap.setMyLocationEnabled(true);

    }

    public void initView() {
        adapter = new SearchAddressAdapter(context, data);
        et_search = (EditText) findViewById(R.id.et_search);

        lv_address = (ListView) findViewById(R.id.lv_address);
        lv_address.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回上级Activity
            case android.R.id.home:
                //销毁地图
                onDestroy();
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    //解析返回的结果，获取输入提示返回的信息
    @Override
    public void onGetInputtips(List<Tip> list, int i) {

    }


    private void seach(String address) {
        query = new PoiSearch.Query(address, "", "");
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(page);//设置查询页码

        poiSearch = new PoiSearch(this, query);
        poiSearch.searchPOIAsyn();//调用 PoiSearch 的 searchPOIAsyn() 方法发送请求。

        poiSearch.setOnPoiSearchListener(this);

        //周边检索
        //   poiSearch.setBound(new SearchBound(new LatLonPoint(locationMarker.getPosition().latitude,
        //          locationMarker.getPosition().longitude), 1000));//设置周边搜索的中心点以及半径
    }

    private void setOnClick() {

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = et_search.getText().toString();
                if (str.length() > 0) {
                    seach(str);
                }
            }
        });

        lv_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();//ListView item点击回调
                intent.putExtra("data", data.get(i));
                setResult(100, intent);
                finish();
            }
        });

    }
//解析查询结果
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        data.clear();
        //解析result获取POI信息
        if (i == 1000 && poiResult != null) {
            ArrayList<PoiItem> items = poiResult.getPois();
            for (PoiItem item : items) { //获取经纬度对象
                LatLonPoint llp = item.getLatLonPoint();
                double lon = llp.getLongitude();
                double lat = llp.getLatitude();

                //获取标题
                String title = item.getTitle();
                //获取内容
                String text = item.getSnippet();
                String name = item.getAdName();
                String cityName = item.getCityName();
                String area = item.getBusinessArea();
                String provinceName = item.getProvinceName();

                String addressInfo = provinceName + cityName + name + text;

                data.add(new AddressBean(lon, lat, title, text, addressInfo));
            }
            adapter.refreshData(data);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

}
