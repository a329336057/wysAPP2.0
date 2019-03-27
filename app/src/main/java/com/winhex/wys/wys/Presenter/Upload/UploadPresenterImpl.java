package com.winhex.wys.wys.Presenter.Upload;

import com.winhex.wys.wys.Model.UploadModel.UploadMode;
import com.winhex.wys.wys.View.IUploadView;
import com.winhex.wys.wys.bean.Uploadbean;

import java.util.List;

import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.schedulers.Schedulers;


public class UploadPresenterImpl implements IUploadPresenter {
    private UploadMode uploadMode;
    private IUploadView view;

    public UploadPresenterImpl(IUploadView view){
        uploadMode  =uploadMode.getInstance();
        this.view=view;
    }
    @Override
    public void getUpload(String baseUrl, List<MultipartBody.Part> parts,String token,String type,String text,String localpostion) {


        uploadMode.getUpload(baseUrl, parts,token,type,text,localpostion)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Uploadbean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getDataFailed(e);
                    }

                    @Override
                    public void onNext(Uploadbean uploadbean) {
                        view.getDateSuccess(uploadbean);
                    }

                });
    }
}
