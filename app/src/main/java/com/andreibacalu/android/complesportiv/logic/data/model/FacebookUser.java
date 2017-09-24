package com.andreibacalu.android.complesportiv.logic.data.model;

/**
 * Created by andrei on 23/09/2017.
 */

public class FacebookUser {
    String id;
    String firstName;
    String lastName;
    String email;

    public FacebookUser(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
