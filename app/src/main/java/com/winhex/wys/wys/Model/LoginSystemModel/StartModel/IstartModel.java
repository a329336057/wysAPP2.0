package com.winhex.wys.wys.Model.LoginSystemModel.StartModel;

import com.winhex.wys.wys.bean.Startokenbean;

import rx.Observable;

public interface IstartModel {
   
    Observable<Startokenbean> getValidationToken(String baseUrl, String token);

}
