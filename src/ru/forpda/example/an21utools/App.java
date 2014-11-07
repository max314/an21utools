package ru.forpda.example.an21utools;

import android.app.Application;
import com.stericson.RootTools.RootTools;
import ru.forpda.example.an21utools.util.LogHelper;

/**
 * Приложение
 * Created by max on 30.10.2014.
 */
public class App extends Application {
    private static LogHelper Log = new LogHelper(App.class);

    public static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                Log.e("Unhandled ", ex);
            }
        });

        RootTools.debugMode = true;
        Log.d("Application start");
    }
}
