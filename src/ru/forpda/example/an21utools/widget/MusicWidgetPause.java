package ru.forpda.example.an21utools.widget;

import android.content.Context;
import ru.forpda.example.an21utools.R;
import ru.forpda.example.an21utools.model.ModelFactory;
import ru.forpda.example.an21utools.util.LogHelper;

/**
 * Created by max on 12.11.2014.
 */
public class MusicWidgetPause extends MusicWidgetBase {
    private static LogHelper Log = new LogHelper(MusicWidgetPause.class);


    @Override
    protected int getWidgetId() {
        return R.layout.widgetmusic_pause;
    }

    @Override
    protected int getWidgetCleckabelViewId() {
        return R.id.widgetMusicImageView;
    }

    @Override
    protected void onClick(Context context) {
        ModelFactory.getAutoRunModel().getMusicOperation().pause();
        Log.d("onClick");
    }
}
