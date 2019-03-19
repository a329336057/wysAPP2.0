package com.winhex.wys.wys.Model.LoginSystemModel.LoginModel;

import com.winhex.wys.wys.bean.Loginbean;

import rx.Observable;

public interface ILoginModel {
    Observable<Loginbean> getlogins(String baseUrl, String username, String password);

}
