package ru.forpda.example.an21utools.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import ru.forpda.example.an21utools.util.LogHelper;

/**
 * Created by max on 12.11.2014.
 */
public abstract class MusicWidgetBase extends AppWidgetProvider {
    public static final String MUSIC_WIDGET_COMMAND = "ru.forpda.example.an21utools.musicwidgetclick";
    private static LogHelper Log = new LogHelper(MusicWidgetBase.class);

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d("onEnabled");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d("onDisabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d("onUpdate");

        RemoteViews widgetView = new RemoteViews(context.getPackageName(), getWidgetId());


        Intent testIntent = new Intent(context, this.getClass());
        testIntent.setAction(MUSIC_WIDGET_COMMAND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, testIntent, 0);
        widgetView.setOnClickPendingIntent(getWidgetCleckabelViewId(), pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds,widgetView);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d("onDelete");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equalsIgnoreCase(MUSIC_WIDGET_COMMAND)){
            onClick(context);
        }
    }

    /**
     * Получить ид лайота виджета
     * @return
     */
    protected abstract int getWidgetId();

    /**
     * получит ид кликательной области внутри лайоута виджета
     * @return
     */
    protected abstract int getWidgetCleckabelViewId();

    /**
     * То что делаем при клике
     * @param context
     */
    protected abstract void onClick(Context context);


}
