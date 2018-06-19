package com.andreibacalu.android.complesportiv.login;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;

import com.andreibacalu.android.complesportiv.logic.data.api.ApiService;
import com.andreibacalu.android.complesportiv.logic.data.model.AvailableSlotsResponse;
import com.andreibacalu.android.complesportiv.logic.data.model.FacebookUser;
import com.andreibacalu.android.complesportiv.logic.data.model.LoginResponse;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.Calendar;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * Created by andrei on 23/09/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    private final WeakReference<LoginContract.View> view;
    private final ApiService apiService;

    public LoginPresenter(LoginContract.View view, ApiService apiService) {
        this.view = new WeakReference<LoginContract.View>(view);
        this.apiService = apiService;
    }

    @Override
    public void onFacebookLoginSuccessful(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            final GraphResponse response) {
                        Log.e(TAG, "onCompleted: " + object + " --- " + response);
                        apiService.login(new FacebookUser(object.optString("id", ""),
                                object.optString("first_name", ""),
                                object.optString("last_name", ""),
                                object.optString("email", "")))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new DisposableObserver<LoginResponse>() {
                                @Override
                                public void onError(Throwable e) {
                                    Timber.e(e, "Error during book retrieval.");
                                }

                                @Override
                                public void onComplete() {
                                    Timber.d("onComplete.");
                                }

                                @Override
                                public void onNext(LoginResponse loginResponse) {
                                    Timber.d("onNext: %s", loginResponse);
                                }
                            });
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void getAvailableSlots() {
        long currentTime = (Calendar.getInstance().getTimeInMillis() + (11 * DateUtils.DAY_IN_MILLIS)) / 1000;
        apiService.getAvailableSlots(currentTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AvailableSlotsResponse>() {
                    @Override
                    public void onNext(AvailableSlotsResponse availableSlotsResponse) {
                        Timber.d("onNext: %s", availableSlotsResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Error during available slots retrieval.");
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete");
                    }
                });
    }
}
