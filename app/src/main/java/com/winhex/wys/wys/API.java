package com.winhex.wys.wys;

import com.winhex.wys.wys.bean.Informationbean;
import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.bean.Registerbean;
import com.winhex.wys.wys.bean.Startokenbean;
import com.winhex.wys.wys.bean.Uploadbean;


import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface API {

    @GET("dr/login")
    Observable<Loginbean> getlogin(@Query("username")String username, @Query("password")String password);

    @GET("dr/register")
    Observable<Registerbean> getregister(@Query("username")String username, @Query("password")String password);
    
    @GET("dr/tokencheck")
    Observable<Startokenbean>ValidationToken(@Header("token") String token);

    @GET("dr/information")
    Observable<Informationbean>geinformation(@Query("nickname")String nickname,
                                             @Query("gender") String gender,
                                             @Query("birthday") String Birthday,
                                             @Query("height") String height,
                                             @Query("phone") String phone,
                                             @Query("username") String username);

    @Multipart
    @POST("upload/filesUpload")
    Observable<Uploadbean>getUpload(@Part()List<MultipartBody.Part> parts);
}
//    /**
//     * 上传图片
//     *
//     * @param file 头像文件
//     * @return 结果
//     */
//    @Multipart
//    @POST("api/upload/image")
//    Observable<UserImageResponse> uploadAvatarFile(@Part MultipartBody.Part file);



//OpenPlatApi.getSmartCallService()
//        .uploadAvatarFile(OpenPlatApi.createMultipart("file", contentType, file))
//        .compose(view.applySchedulers())
//        .subscribe(object :SmartCallNetworkObserver<ICollectContract.View, UserImageResponse>(this) {
//        override fun onSuccess(view: ICollectContract.View, data: UserImageResponse) {
//        view.onResponseUploadImage(true, data)
//        }
//
//        override fun onFail(view: ICollectContract.View, data: UserImageResponse) {
//        view.onResponseUploadImage(false, null)
//        }
//
//        override fun onError(view: ICollectContract.View, throwable: Throwable) {
//        view.onResponseUploadImage(false ,null)
//        }
//
//        override fun onInitialize(view: ICollectContract.View): NetworkOption {
//        view.showLoading()
//        return super.onInitialize(view)
//        }
//        })

//    /**
//     * 通过文件创建Multipart
//     *
//     * @param formDataName 表单名
//     * @param contentType 文件的content-type
//     * @param file 文件
//     * @return multipart对象
//     */
//    public static MultipartBody.Part createMultipart(String formDataName, String contentType, File file) {
//        // 参数检查
//        if (TextUtils.isEmpty(formDataName) || file == null || !file.exists()) {
//            return null;
//        }
//        // content-type对应文件的类型，例如：image/jpg, image/png。
//        contentType = contentType == null ? "" : contentType;
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream(file);
//            RequestBody body = RequestBody.create(MediaType.parse(contentType), ConvertUtils.inputStream2Bytes(fileInputStream));
//            return MultipartBody.Part.createFormData(formDataName, file.getName(), body);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            CloseUtils.closeIO(fileInputStream);
//        }
//        return null;
//    }