package ru.forpda.example.an21utools;

import android.app.Application;

/**
 * Created by max on 30.10.2014.
 */
public class App extends Application {

    public static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
