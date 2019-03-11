package com.winhex.wys.wys.View;

        import com.winhex.wys.wys.bean.Startokenbean;

public interface Istartview {
    void getDataFailed(Throwable e);
    void getStartSuccess(Startokenbean startokenbean);
}
