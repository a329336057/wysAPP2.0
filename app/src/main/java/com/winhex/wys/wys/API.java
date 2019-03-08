package com.winhex.wys.wys;

import com.winhex.wys.wys.bean.Loginbean;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface API {

    @GET("dr/login")
    Observable<Loginbean> getlogin(@Query("usernmae")String username, @Query("password")String password);
}
