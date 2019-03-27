package com.winhex.wys.wys.Activity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.winhex.wys.wys.Activity.Adapter.LateralAdapter;
import com.winhex.wys.wys.Activity.Release.Publish;
import com.winhex.wys.wys.Presenter.Home.HomePresenterImpl;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.GlideImageLoader;
import com.winhex.wys.wys.Utils.SharedPreferencesUtil;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.Utils.UrlIPconfig;
import com.winhex.wys.wys.View.IHomeview;
import com.winhex.wys.wys.bean.Homebean;
import com.winhex.wys.wys.bean.lateralBean;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragent extends Fragment implements OnBannerListener, OnTitleBarListener,IHomeview{
    SmartRefreshLayout msmartlayout;
    Banner mbanner;
    List<lateralBean> list_lateral=new ArrayList<>();
    NestedScrollView scrollView;
    TitleBar titleBar;
    RecyclerView MlateralrecyclerView;
    HomePresenterImpl homePresenter;
    LateralAdapter lateralAdapter;

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public  void  handleMessage(Message message){
                if(message.what==1){
                    lateralAdapter=new LateralAdapter(list_lateral,getContext());
                    MlateralrecyclerView.setAdapter(lateralAdapter);
                    lateralAdapter.notifyDataSetChanged();

                        recyclerViewsetting();
                    ToastUtils.show(getContext(),"刷新成功");
                }
        }
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home_fragent, container, false);
        findid(v);
        bannerSetting();
        homePresenter=new HomePresenterImpl(this);
        flish();
        SharedPreferencesUtil.getInstance(getContext(),"tokens");
        homePresenter.getHomeData(UrlIPconfig.GONGSIIP,(String) SharedPreferencesUtil.getData("token","没有token"));
        return v;

    }

    private void flish() {
    msmartlayout.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            SharedPreferencesUtil.getInstance(getContext(),"tokens");
            homePresenter.getHomeData(UrlIPconfig.GONGSIIP,(String) SharedPreferencesUtil.getData("token","没有token"));
            refreshLayout.finishRefresh(1000);
        }
    });

    }


    /**
     * 设置样式
     */
    private void recyclerViewsetting() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
       // MlateralrecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));//横线
        MlateralrecyclerView.setLayoutManager(linearLayoutManager);

    }

    /**
     * banner设置
     */
    private void bannerSetting() {
        mbanner.setImageLoader(new GlideImageLoader());
        final List<String> list=new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552812527473&di=49786bdc1937ae4a28c4c01e709dcd10&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-12-05%2F5a262f421fccd.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552814100617&di=ccd90978b039c1b990ab8841f3e85460&imgtype=0&src=http%3A%2F%2Fimg2.ph.126.net%2FhZSpKs9G6jUlTS6J-e-xoA%3D%3D%2F6631417410401878349.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552814100616&di=3fd6ebe396d1001037b12fcccb374ea6&imgtype=0&src=http%3A%2F%2Fimg2.ph.126.net%2FC56h7v-DL3caFkFTxdn7dw%3D%3D%2F6630472929908798903.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552814100614&di=a925f47f0fae4b3f6aae83ebac3af526&imgtype=0&src=http%3A%2F%2Fimg2.ph.126.net%2F8qSIEmPAwjnxDLtUZVXb7A%3D%3D%2F6631720875607361156.jpg");
        mbanner.setImages(list)
                .setBannerAnimation(Transformer.DepthPage).setOnBannerListener(this).start();
    }

    /**
     * 查询ID
     * @param v
     */
    private void findid(View v) {
        list_lateral=new ArrayList<>();

        msmartlayout=v.findViewById(R.id.refreshLayout);
        MlateralrecyclerView=v.findViewById(R.id.lateral);
        titleBar=v.findViewById(R.id.hometitel);

        scrollView=v.findViewById(R.id.home_huadong);
        scrollView.smoothScrollTo(0,20);
        mbanner=v.findViewById(R.id.banner);
        scrollView.requestFocus();
        titleBar.setOnTitleBarListener(this);

        MlateralrecyclerView.setNestedScrollingEnabled(false);

    }

    /**
     * 滚动条
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (scrollView!= null){
            scrollView.fullScroll(View.FOCUS_UP);
        }
    }


    /**
     * 标题点击
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        ToastUtils.show(getContext(),"你点击了"+position);
    }

    @Override
    public void onLeftClick(View v) {

    }

    @Override
    public void onTitleClick(View v) {

    }

  
    @Override
    public void onRightClick(View v) {
        Intent intent=new Intent(getActivity(),Publish.class);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * 数据获取接口
     * @param e
     */
    
    @Override
    public void getDataFailed(Throwable e) {

        ToastUtils.show(getContext(),e.getMessage());
    }

    @Override
    public void getDataSuccess(Homebean homebean) {
                if(homebean.getCode()==200){
            if(homebean.getHomerow()!=null){
                //发送messgae
                list_lateral.clear();
                addbean(homebean);
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }else {
                ToastUtils.show(getContext(),homebean.getMeassage());
            }
        }
    }

    /**
     * 接口数据赋值
     * @param homebean
     */
    void addbean(Homebean homebean){
        List<Homebean.HomerowBean> list=homebean.getHomerow();
        int a=3;
        for (int i = 0; i < list.size(); i++) {
            lateralBean lateralBean=new lateralBean();
            lateralBean.setContent(list.get(i).getContent());
            lateralBean.setImage(list.get(i).getImage());
            lateralBean.setHead_portrait(R.drawable.classify_1);
            list_lateral.add(lateralBean);

        }

    }
}
