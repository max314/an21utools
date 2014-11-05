package ru.forpda.example.an21utools;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import ru.forpda.example.an21utools.util.LogHelper;

/**
 * Created by max on 31.10.2014.
 * Воновый сервис предполагаеться что это он будеть запускать приложения
 */
public class BackgroudService extends IntentService  {

    public BackgroudService() {
        super("BackgroudService");
    }

    private static LogHelper Log = new LogHelper(BackgroudService.class);
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
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

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int notifyID = 1;

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Автозапуск")
                .setContentText("Эбу бу бу")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentInfo("xvcbcvxbxcvbxcvb")
                .setSubText("sub text")
                .build();
        notificationManager.notify(notifyID,notification);

       startForeground(notifyID,notification);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopForeground(true);
        notificationManager.cancel(notifyID);

    }
}
