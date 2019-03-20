package com.winhex.wys.wys.Presenter.Home;

import com.winhex.wys.wys.Model.HomeModel.HomeMode;
import com.winhex.wys.wys.View.IHomeview;
import com.winhex.wys.wys.bean.Homebean;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class HomePresenterImpl implements IHomePresenter {
    private HomeMode homeMode;
    private IHomeview view;

    public HomePresenterImpl(IHomeview view){
        homeMode  =homeMode.getInstance();
        this.view=view;
    }
    @Override
    public void getHomeData(String baseUrl, String token) {
        homeMode.getHomeData(baseUrl, token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Homebean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getDataFailed(e);
                    }

                    @Override
                    public void onNext(Homebean homebean) {
                        view.getDataSuccess(homebean);
                    }

                });
    }
}
