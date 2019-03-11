package com.winhex.wys.wys.Model.LoginModel;

import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.bean.Startokenbean;

import rx.Observable;

public interface ILoginModel {
    Observable<Loginbean> getlogins(String baseUrl, String username, String password);

}
