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
import android.widget.LinearLayout;

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


public class MyFragment extends Fragment {
    LinearLayout mloginout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my, container, false);
        mloginout=v.findViewById(R.id.login_out);

        signout();

        return v;

        /**
         * 退出登陆
         */

    }


    private void signout() {
        mloginout.setOnClickListener(new View.OnClickListener() {
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


}
