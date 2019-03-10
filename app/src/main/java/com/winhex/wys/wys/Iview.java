package com.winhex.wys.wys;

import com.winhex.wys.wys.bean.Loginbean;

public interface Iview {
   public   void getDataFailed(Throwable e);
   public void getDataSuccess(Loginbean loginbean);
}
