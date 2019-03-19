package com.winhex.wys.wys.Model.UploadModel;

import com.winhex.wys.wys.bean.Uploadbean;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import rx.Observable;

public interface IUploadModel {
    Observable<Uploadbean> getUpload(String url, List<MultipartBody.Part> part);
}
