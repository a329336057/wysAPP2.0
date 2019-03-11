package com.winhex.wys.wys.Presenter.Login;

import com.winhex.wys.wys.Model.LoginModel.ModelLogin;
import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.View.Iloginview;


import rx.Subscriber;
import rx.schedulers.Schedulers;


public class LoginImpl implements ILogin {
    private ModelLogin modelLogin;
    private Iloginview view;
    
    public LoginImpl(Iloginview view){
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
