package com.andreibacalu.android.complesportiv.login;

import android.os.Bundle;
import android.util.Log;

import com.andreibacalu.android.complesportiv.logic.data.api.ApiService;
import com.andreibacalu.android.complesportiv.logic.data.model.FacebookUser;
import com.andreibacalu.android.complesportiv.logic.data.model.LoginResponse;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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
                            .subscribe(new Subscriber<LoginResponse>() {
                                @Override
                                public void onCompleted() {
                                    Timber.d("Complete.");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Timber.e(e, "Error during book retrieval.");
                                }

                                @Override
                                public void onNext(LoginResponse loginResponse) {
                                    Timber.d("onNext: " + loginResponse);
                                }
                            });
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
