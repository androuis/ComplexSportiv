package com.andreibacalu.android.complesportiv.logic.data.model;

/**
 * Created by andrei on 24/09/2017.
 */

public class LoginResponse {
    public LoginResponseData data;
    public int success;

    public class LoginResponseData {
        public User customer;
        public String email;
    }
}
