package com.winhex.wys.wys.Model.InformationModel;

import com.winhex.wys.wys.Model.RegisterModel.RegisterMode;
import com.winhex.wys.wys.RetrofitFactory;
import com.winhex.wys.wys.bean.Informationbean;

import rx.Observable;

public class InformationMode implements IInformationModel {
    private static InformationMode instance;

    public synchronized static InformationMode getInstance() {

        if (null == instance) {
            synchronized (InformationMode.class) {
                instance = new InformationMode();
            }
        }

        return instance;
    }

    @Override
    public Observable<Informationbean> geinformation(String baseUrl,String nickname,String gender, String Birthday, String height, String phone,String username) {
        return RetrofitFactory.getInstance().getCustomHaierAPi(baseUrl).geinformation(nickname,gender,Birthday,height,phone,username);
    }
}
