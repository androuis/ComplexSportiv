package com.andreibacalu.android.complesportiv.logic.data.api;

import com.andreibacalu.android.complesportiv.logic.data.model.FacebookUser;
import com.andreibacalu.android.complesportiv.logic.data.model.LoginResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by andrei on 23/09/2017.
 */

public interface ApiService {
    @POST("authenticate/social")
    Observable<LoginResponse> login(@Body FacebookUser facebookUser);
}
