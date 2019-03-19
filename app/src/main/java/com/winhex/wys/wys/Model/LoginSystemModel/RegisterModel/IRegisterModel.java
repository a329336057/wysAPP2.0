package com.winhex.wys.wys.Model.LoginSystemModel.RegisterModel;

import com.winhex.wys.wys.bean.Registerbean;

import rx.Observable;

public interface IRegisterModel {
    Observable<Registerbean> getRegister(String baseUrl, String username, String password) ;

}
