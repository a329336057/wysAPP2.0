package com.winhex.wys.wys.Activity.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amap.api.maps.MapView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.winhex.wys.wys.Activity.MainActivity;
import com.winhex.wys.wys.LoginSystemActivity.Login;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.SharedPreferencesUtil;
import com.winhex.wys.wys.Utils.ToastUtils;


public class MyFragment extends Fragment implements GeocodeSearch.OnGeocodeSearchListener {
    Button mSignOut;
    MapView mapView;
    GeocodeSearch geocodeSearch;
    LatLonPoint latLonPoint;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my, container, false);
        mSignOut=v.findViewById(R.id.SignOut);
        mapView=v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        latLonPoint=new LatLonPoint(39.90403, 116.407525);
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String []{android.Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }
        geocodeSearch=new GeocodeSearch(getContext());
        geocodeSearch.setOnGeocodeSearchListener(this);
        RegeocodeQuery query=new RegeocodeQuery(latLonPoint,200,GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);
        signout();
        return v;

        /**
         * 退出登陆
         */

    }


    private void signout() {
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.getInstance(getContext(),"tokens");
                SharedPreferencesUtil.Remove("token");
                Intent intent=new Intent(getContext(),Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        int a=i;
        ToastUtils.show(getContext(),regeocodeResult.getRegeocodeAddress().getFormatAddress());
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        int a=i;
        ToastUtils.show(getContext(),geocodeResult.getGeocodeAddressList().toString());
    }
}
