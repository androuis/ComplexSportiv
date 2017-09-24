package com.andreibacalu.android.complesportiv.login;

import com.facebook.login.LoginResult;

/**
 * Created by andrei on 23/09/2017.
 */

public interface LoginContract {

    interface Presenter {

        void onFacebookLoginSuccessful(LoginResult loginResult);
    }

    interface View {

    }
}
