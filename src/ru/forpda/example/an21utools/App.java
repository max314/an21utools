package ru.forpda.example.an21utools;

import android.app.Application;
import ru.forpda.example.an21utools.util.LogHelper;

/**
 * Created by max on 30.10.2014.
 */
public class App extends Application {
    private static LogHelper Log = new LogHelper(App.class);

    public static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.d("Application start");
    }
}
