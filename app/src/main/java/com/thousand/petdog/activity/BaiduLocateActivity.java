package com.thousand.petdog.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.thousand.petdog.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaiduLocateActivity extends AppCompatActivity {

    @BindView(R.id.bmapView)
    MapView mMapView;
    private ArrayAdapter<String> sugAdapter = null;
    private SuggestionSearch mSuggestionSearch = null;

    @BindView(R.id.search)
    AutoCompleteTextView keyWorldsView;
    private BaiduMap mBaiduMap;

    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;

    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private float direction;

    private PoiSearch mPoiSearch;
    private LatLng center = new LatLng(39.92235, 116.380338);
    private int radius = 100;
    private LatLng southwest = new LatLng(39.92235, 116.380338);
    private LatLng northeast = new LatLng(39.947246, 116.414977);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_locate);
        ButterKnife.bind(this);

        mBaiduMap = mMapView.getMap();

        // 设置定位数据
//        mBaiduMap.setMyLocationData(locData);
        //定位模式
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        //自定义定位图标
        //mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.icon_start);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);

        mBaiduMap.setMyLocationEnabled(true);    //开启定位图层
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        // 当不需要定位图层时关闭定位图层
        //mBaiduMap.setMyLocationEnabled(false);

        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult res) {
                if (res == null || res.getAllSuggestions() == null) {
                    return;
                }

                List<String> suggest = new ArrayList<>();
                for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
                    if (info.key != null) {
                        suggest.add(info.key);
                    }
                }

                sugAdapter = new ArrayAdapter<>(BaiduLocateActivity.this, android.R.layout.simple_dropdown_item_1line,
                        suggest);
                keyWorldsView.setAdapter(sugAdapter);
                sugAdapter.notifyDataSetChanged();
            }
        });

        //搜索
        sugAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        keyWorldsView.setAdapter(sugAdapter);
        keyWorldsView.setThreshold(1);

        /* 当输入关键字变化时，动态更新建议列表 */
        keyWorldsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    return;
                }

                /* 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新 */
                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
                        .keyword(cs.toString())
                        .city("郑州"));

            }
        });

        mPoiSearch = PoiSearch.newInstance();

        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {

            public void onGetPoiResult(PoiResult result) {
                //获取POI检索结果
                System.out.println("获取POI检索结果=====================>" + String.valueOf(result.getTotalPoiNum()) + result.getAllPoi());
                if (result.getAllPoi() != null) {
                    List<PoiInfo> list = result.getAllPoi();
                    for (int i = 0; i < list.size(); i++) {
                        PoiInfo poiInfo = list.get(i);
                        System.out.println("=====================>店名" + poiInfo.name + "具体地址" + poiInfo.address);
                    }
                }
            }

            public void onGetPoiDetailResult(PoiDetailResult result) {
                //获取Place详情页检索结果
                System.out.println("获取Place详情页检索结果=====================>" + result.getName() + "地址：" + result.getAddress());

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
                System.out.println("获取POI检索结果详情=====================>" + poiDetailSearchResult.getPoiDetailInfoList());
                List<PoiDetailInfo> list = poiDetailSearchResult.getPoiDetailInfoList();
                for (int i = 0; i < list.size(); i++) {
                    PoiDetailInfo poiDetailInfo = list.get(i);
                    System.out.println("=====================>店名：" + poiDetailInfo.getName() + "，具体地址：" + poiDetailInfo.getAddress());
                }
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                System.out.println("室内检索结果详情=====================>" + poiIndoorResult.toString());
            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);

    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            // 构造定位数据
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }

            //城市内检索
            System.out.println("城市名=====================>" + location.getAddrStr() + location.getLocationDescribe());
            mPoiSearch.searchInCity((new PoiCitySearchOption())
                    .city("郑州")
                    .keyword("宠物")
                    .pageNum(10));
        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mPoiSearch.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}

