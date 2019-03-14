package com.winhex.wys.wys.Activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.winhex.wys.wys.Activity.MainActivity;
import com.winhex.wys.wys.LoginSystemActivity.Login;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.SharedPreferencesUtil;


public class MyFragment extends Fragment {
    Button mSignOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my, container, false);
        mSignOut=v.findViewById(R.id.SignOut);
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

}
