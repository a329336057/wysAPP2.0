package com.winhex.wys.wys.Model;

import com.winhex.wys.wys.bean.Loginbean;

import rx.Observable;

public interface ILoginModel {
    Observable<Loginbean> getlogins(String baseUrl, String username, String password);
}
