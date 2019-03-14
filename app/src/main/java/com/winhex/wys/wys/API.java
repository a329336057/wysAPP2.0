package com.winhex.wys.wys;

import com.winhex.wys.wys.bean.Informationbean;
import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.bean.Registerbean;
import com.winhex.wys.wys.bean.Startokenbean;


import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.Header;
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

}
