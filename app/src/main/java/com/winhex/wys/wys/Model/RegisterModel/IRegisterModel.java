package com.winhex.wys.wys.Model.RegisterModel;

import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.bean.Registerbean;
import com.winhex.wys.wys.bean.Startokenbean;

import rx.Observable;

public interface IRegisterModel {
    Observable<Registerbean> getRegister(String baseUrl, String username, String password) ;

}
