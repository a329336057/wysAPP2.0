package com.winhex.wys.wys.Presenter;

import android.view.View;

import com.winhex.wys.wys.Model.ModelLogin;
import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.Iview;



import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;


public class PresenterImpl implements IPresenter {
    private ModelLogin modelLogin;
    private Iview view;
    public PresenterImpl(Iview view){
        modelLogin=ModelLogin.getInstance();
        this.view=view;
    }
    @Override
    public void login(String url, String username, String password) {
       modelLogin.getlogins(url, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Loginbean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getDataFailed(e);
                    }

                    @Override
                    public void onNext(Loginbean loginbean) {
                        view.getDataSuccess(loginbean);
                    }

                });
    }
    
}
