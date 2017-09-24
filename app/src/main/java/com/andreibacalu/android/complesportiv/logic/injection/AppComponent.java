package com.andreibacalu.android.complesportiv.logic.injection;

import com.andreibacalu.android.complesportiv.login.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrei on 23/09/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}
