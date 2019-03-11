package com.winhex.wys.wys.View;

import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.bean.Startokenbean;

public interface Iloginview {
        void getDataFailed(Throwable e);
        void getDataSuccess(Loginbean loginbean);
 }
