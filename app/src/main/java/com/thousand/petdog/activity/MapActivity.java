package com.thousand.petdog.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
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
public class MapActivity extends Activity implements PoiSearch.OnPoiSearchListener, Inputtips.InputtipsListener, View.OnClickListener, LocationSource {
    private Context context;
    private MapView mMapView = null;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private InputtipsQuery inputquery;
    private Inputtips inputtips = null;
    private PoiSearch.Query query;
    private ListView lv_address;
    private PoiSearch poiSearch;
    private SearchAddressAdapter adapter;
    private ArrayList<AddressBean> data = new ArrayList<AddressBean>();
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption;
    int page = 0;
    private EditText et_search;
    private Button btn_search;
    //返回箭头
    @BindView(R.id.tv_left_button)
    Button tv_left_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        context = this;
        initView();
        setOnClick();
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //放大18级
        mMapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(18));
        //--------二：定位步骤1-------------初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        // --------二：定位步骤（无2）3-------------实例化UiSettings类对象
        mUiSettings = aMap.getUiSettings();
        //初始化定位
        initLocation();

        //初始化地图中的手势
        initMapFunction();
        //输入自动提示
        //    inputtips.setInputtipsListener(this);
    }


    public void initView() {
        adapter = new SearchAddressAdapter(context, data);
        et_search = (EditText) findViewById(R.id.et_search);
        lv_address = (ListView) findViewById(R.id.lv_address);
        lv_address.setAdapter(adapter);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    Toast.makeText(getApplicationContext(), "功能正在开发中", Toast.LENGTH_SHORT);
                }
                return false;
            }
        });
    }

    public void initMapFunction() {
        //指南针
        mUiSettings.setCompassEnabled(true);
        //缩放手势
        mUiSettings.setZoomGesturesEnabled(true);
        //滑动手势
        mUiSettings.setScrollGesturesEnabled(true);

    }

    public void initLocation() {
        // --------二：定位步骤4-------------通过aMap对象设置定位数据源的监听
        aMap.setLocationSource(this);
        // --------二：定位步骤5-------------显示默认的定位按钮
        mUiSettings.setMyLocationButtonEnabled(true);
        //  --------二：定位步骤6-------------可触发定位并显示当前位置
        aMap.setMyLocationEnabled(true);
        // --------二：定位步骤7-------------初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //--------二：定位步骤8-------------设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //声明AMapLocationClientOption对象
        mLocationOption = null;
        //--------二：定位步骤9-------------初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //--------二：定位步骤10-------------设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //--------二：定位步骤11-------------//设置是否返回地址信息(默认返回地址信息)
        mLocationOption.setNeedAddress(true);
        //--------二：定位步骤12-------------获取一次定位结果：//该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //--------二：定位步骤13-------------设置是否强制刷新WIFI,默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //--------二：定位步骤14-------------设置是否允许模拟位置,默认为false,不允许模拟位置
        mLocationOption.setMockEnable(false);
        //--------二：定位步骤15-------------设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //--------二：定位步骤16-------------给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //--------二：定位步骤17-------------启动定位
        mLocationClient.startLocation();

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        // 设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        //  myLocationStyle.interval(2000);

        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //初始化定位蓝点样式类
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）
        // 如果不设置myLocationType，默认也会执行此种模式。

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

    //---------------------地图生命周期
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

    //-       -----------------地图生命周期-结束
    @OnClick(R.id.tv_left_button)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left_button:
                finish();
                break;


        }
    }


    private void seach(String address) {
        query = new PoiSearch.Query(address, "", "");
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(page);//设置查询页码

        poiSearch = new PoiSearch(this, query);
        poiSearch.searchPOIAsyn();//调用 PoiSearch 的 searchPOIAsyn() 方法发送请求。

        poiSearch.setOnPoiSearchListener(this);


    }

    private void setOnClick() {

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String content = charSequence.toString().trim();//获取自动提示输入框的内容
                inputquery = new InputtipsQuery(content, "");
                inputquery.setCityLimit(true);//限制在当前城市
                inputtips = new Inputtips(MapActivity.this, inputquery);//定义一个输入提示对象，传入当前上下文和搜索对象
                inputtips.setInputtipsListener(MapActivity.this);//设置输入提示查询的监听，实现输入提示的监听方法onGetInputtips()
                inputtips.requestInputtipsAsyn();//输入查询提示的异步接口实现

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
    // 通过回调接口 onGetInputtips 解析返回的结果，获取输入提示返回的信息

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        if (i == 1000) {//如果输入提示搜索成功

        }
    }

    //POI解析查询结果
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


    //定位3接口
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
