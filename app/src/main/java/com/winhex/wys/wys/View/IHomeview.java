package com.winhex.wys.wys.View;

import com.winhex.wys.wys.bean.Homebean;
import com.winhex.wys.wys.bean.Informationbean;

public interface IHomeview {
    void getDataFailed(Throwable e);
    void getDataSuccess(Homebean homebean);
}
