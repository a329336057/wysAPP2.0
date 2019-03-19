package com.winhex.wys.wys.Presenter.LoginSystemPresenter.Start;

import com.winhex.wys.wys.Model.LoginSystemModel.StartModel.StartMode;
import com.winhex.wys.wys.View.Istartview;
import com.winhex.wys.wys.bean.Startokenbean;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class StartPresenterImpl implements IstartPresenter {
    StartMode startMode;
    Istartview istartview;
    public StartPresenterImpl(Istartview view){
        startMode= StartMode.getInstance();
        this.istartview=view;
    }
    @Override
    public void startApp(String url, String token) {
        startMode.getValidationToken(url,token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Startokenbean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        istartview.getDataFailed(e);
                    }

                    @Override
                    public void onNext(Startokenbean startokenbean) {
                        istartview.getStartSuccess(startokenbean);
                    }

                });
    }
}
