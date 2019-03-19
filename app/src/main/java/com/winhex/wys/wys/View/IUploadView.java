package com.winhex.wys.wys.View;


import com.winhex.wys.wys.bean.Uploadbean;

public interface IUploadView {
    void getDataFailed(Throwable e);
    void getDateSuccess(Uploadbean uploadbean);
}
