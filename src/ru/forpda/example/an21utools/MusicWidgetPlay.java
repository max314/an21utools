package ru.forpda.example.an21utools;

import android.content.Context;
import ru.forpda.example.an21utools.model.ModelFactory;
import ru.forpda.example.an21utools.util.LogHelper;

/**
 * Created by max on 12.11.2014.
 */
public class MusicWidgetPlay extends MusicWidgetBase {
    private static LogHelper Log = new LogHelper(MusicWidgetPlay.class);


    @Override
    protected int getWidgetId() {
        return R.layout.widgetmusic_play;
    }

    @Override
    protected int getWidgetCleckabelViewId() {
        return R.id.widgetMusicImageView;
    }

    @Override
    protected void onClick(Context context) {
        ModelFactory.getAutoRunModel().getMusicOperation().play();
        Log.d("onClick");
    }

}
