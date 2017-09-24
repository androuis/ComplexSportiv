package com.andreibacalu.android.complesportiv.logic.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andrei on 24/09/2017.
 */

public class User {
    public String id;
    public String phone;
    @SerializedName("date_created")
    public String dateCreated;
    public String active;
    @SerializedName("city_id")
    public String cityId;
    @SerializedName("newsletter_yn")
    public String newsletterSubscription;
    @SerializedName("credential_id")
    public String credentialId;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("first_name")
    public String firstName;
    public String email;
    public String birthday;
}
