package com.winhex.wys.wys.View;

import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.bean.Registerbean;

public interface Iregisterview {
    void getDataFailed(Throwable e);
    void getDataSuccess(Registerbean registerbean);
}
