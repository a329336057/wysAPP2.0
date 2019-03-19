package com.winhex.wys.wys.Model.LoginSystemModel.InformationModel;

import com.winhex.wys.wys.bean.Informationbean;

import rx.Observable;

public interface IInformationModel {
    Observable<Informationbean> geinformation(String baseUrl,String nickname, String gender, String Birthday, String height, String phone,String username);

}
