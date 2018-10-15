package com.example.chaitali.showsapplication.view;

import android.app.Application;

import com.example.chaitali.showsapplication.database.RoomDatabaseManager;

import di.AppModule;
import di.DaggerIAppComponent;
import di.IAppComponent;
import di.NetworkModule;
import di.ServiceModule;

public class ShowsApplication extends Application {
    private static IAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjection();
    }

    private void initializeDependencyInjection() {
        appComponent = DaggerIAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(this))
                .serviceModule(new ServiceModule())
                .build();
    }

    public static IAppComponent getAppComponent() {
        return appComponent;
    }

}
