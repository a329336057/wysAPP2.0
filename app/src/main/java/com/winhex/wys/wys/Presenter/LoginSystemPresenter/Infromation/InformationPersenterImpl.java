package com.winhex.wys.wys.Presenter.LoginSystemPresenter.Infromation;

import com.winhex.wys.wys.Model.LoginSystemModel.InformationModel.InformationMode;
import com.winhex.wys.wys.View.IInformationview;
import com.winhex.wys.wys.bean.Informationbean;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class InformationPersenterImpl implements  IInformationPresenter {

    private InformationMode informationMode;
    private IInformationview view;

    public InformationPersenterImpl(IInformationview view){
        informationMode  =informationMode.getInstance();
        this.view=view;
    }
    @Override
    public void geinformation(String baseUrl, String nickname, String gender, String Birthday, String height, String phone, String username) {
        informationMode.geinformation(baseUrl, nickname,gender,Birthday,height,phone,username)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Informationbean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getDataFailed(e);
                    }

                    @Override
                    public void onNext(Informationbean informationbean) {
                        view.getDataSuccess(informationbean);
                    }

                });
    }
}
