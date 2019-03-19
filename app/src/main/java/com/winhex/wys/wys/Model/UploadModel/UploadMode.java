package com.winhex.wys.wys.Model.UploadModel;

import com.winhex.wys.wys.RetrofitFactory;
import com.winhex.wys.wys.bean.Uploadbean;

import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;

public class UploadMode implements IUploadModel {
    private static UploadMode instance;

    public synchronized static UploadMode getInstance() {

        if (null == instance) {
            synchronized (UploadMode.class) {
                instance = new UploadMode();
            }
        }

        return instance;
    }
    @Override
    public Observable<Uploadbean> getUpload(String url, List<MultipartBody.Part>part,String token,String type,String text) {
        return RetrofitFactory.getInstance().getCustomHaierAPi(url).getUpload(part,token,text,type);
    }
}
