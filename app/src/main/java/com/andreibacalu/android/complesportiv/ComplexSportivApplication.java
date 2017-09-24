package com.andreibacalu.android.complesportiv;

import android.app.Application;

import com.andreibacalu.android.complesportiv.logic.injection.AppComponent;
import com.andreibacalu.android.complesportiv.logic.injection.AppModule;
import com.andreibacalu.android.complesportiv.logic.injection.DaggerAppComponent;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import timber.log.Timber;

/**
 * Created by andrei on 21/09/2017.
 */

public class ComplexSportivApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        Timber.plant(new Timber.DebugTree());

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
