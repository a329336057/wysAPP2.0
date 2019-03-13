package com.winhex.wys.wys.Presenter.Register;

import com.winhex.wys.wys.Model.RegisterModel.RegisterMode;
import com.winhex.wys.wys.View.Iregisterview;
import com.winhex.wys.wys.bean.Registerbean;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class RegisterPresenterImpl implements IregisterPresenter{
    private RegisterMode registerMode;
        private Iregisterview view;

    public RegisterPresenterImpl(Iregisterview view){
        registerMode=registerMode.getInstance();
        this.view=view;
    }
    @Override
    public void register(String url, String username, String password) {
        registerMode.getRegister(url, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Registerbean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getDataFailed(e);
                    }

                    @Override
                    public void onNext(Registerbean registerbean) {
                        view.getDataSuccess(registerbean);
                    }

                });
    }

    @Override
    public void geinformation(String baseUrl, String gender, String Birthday, String height, String phone) {
        registerMode.geinformation(baseUrl, gender, Birthday,height,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Registerbean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getDataFailed(e);
                    }

                    @Override
                    public void onNext(Registerbean registerbean) {
                        view.getDataSuccess(registerbean);
                    }

                });
    }
}

