package com.winhex.wys.wys.Model.StartModel;

import com.winhex.wys.wys.RetrofitFactory;
import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.bean.Startokenbean;

import rx.Observable;

public interface IstartModel {
   
    Observable<Startokenbean> getValidationToken(String baseUrl, String token);
}
