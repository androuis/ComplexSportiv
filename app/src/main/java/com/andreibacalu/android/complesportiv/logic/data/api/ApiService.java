package com.andreibacalu.android.complesportiv.logic.data.api;

import com.andreibacalu.android.complesportiv.logic.data.model.AvailableSlotsResponse;
import com.andreibacalu.android.complesportiv.logic.data.model.FacebookUser;
import com.andreibacalu.android.complesportiv.logic.data.model.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by andrei on 23/09/2017.
 */

public interface ApiService {
    @POST("authenticate/social")
    Observable<LoginResponse> login(@Body FacebookUser facebookUser);

    @GET("get_available_slots?service_id=8029&location_id=1651&day_only=1")
    Observable<AvailableSlotsResponse> getAvailableSlots(@Query("date") long date);
}
