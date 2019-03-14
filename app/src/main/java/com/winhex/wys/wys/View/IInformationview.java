package com.winhex.wys.wys.View;

import com.winhex.wys.wys.bean.Informationbean;
import com.winhex.wys.wys.bean.Registerbean;

public interface IInformationview {
    void getDataFailed(Throwable e);
    void getDataSuccess(Informationbean informationbean);
}
