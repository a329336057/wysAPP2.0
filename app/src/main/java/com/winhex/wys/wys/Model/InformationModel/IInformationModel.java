package com.winhex.wys.wys.Model.InformationModel;

import com.winhex.wys.wys.bean.Informationbean;
import com.winhex.wys.wys.bean.Loginbean;

import rx.Observable;

public interface IInformationModel {
    Observable<Informationbean> geinformation(String baseUrl,String nickname, String gender, String Birthday, String height, String phone,String username);

}
