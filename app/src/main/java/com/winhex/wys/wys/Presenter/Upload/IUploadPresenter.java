package com.winhex.wys.wys.Presenter.Upload;

import java.util.List;

import okhttp3.MultipartBody;

public interface IUploadPresenter {
    void geinformation(String baseUrl, List<MultipartBody.Part> parts);
}
