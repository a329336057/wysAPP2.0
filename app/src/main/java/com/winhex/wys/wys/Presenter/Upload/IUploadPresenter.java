package com.winhex.wys.wys.Presenter.Upload;

import java.util.List;

import okhttp3.MultipartBody;

public interface IUploadPresenter {
    void getUpload(String baseUrl, List<MultipartBody.Part> parts,String token,String type,String text,String localpostion);
}
