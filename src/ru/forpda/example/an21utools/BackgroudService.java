package ru.forpda.example.an21utools;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import ru.forpda.example.an21utools.util.LogHelper;

/**
 * Created by max on 31.10.2014.
 * Воновый сервис предполагаеться что это он будеть запускать приложения
 */
public class BackgroudService extends Service {
    private static LogHelper Log = new LogHelper(BackgroudService.class);

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Create");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("OnBind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Destroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int res = super.onStartCommand(intent, flags, startId);
        Notification notification = new Notification(R.drawable.ic_launcher, "Text in status bar", System.currentTimeMillis());
        startForeground(100,notification);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopForeground(true);
        stopSelf(startId);
        return res;
    }
}
