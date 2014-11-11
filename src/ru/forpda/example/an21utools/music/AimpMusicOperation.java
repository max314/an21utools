package ru.forpda.example.an21utools.music;

import android.content.Intent;
import android.view.KeyEvent;
import ru.forpda.example.an21utools.App;

/**
 * Created by max on 11.11.2014.
 * имплементация для aim через сервис
 */
public class AimpMusicOperation implements IMusicOpertion {
    @Override
    public void play() {
        runOperaton("com.aimp.service.action.PLAY");
    }

    @Override
    public void pause() {
        runOperaton("com.aimp.service.action.PAUSE");
    }

    @Override
    public void playPause() {
        runOperaton("com.aimp.service.action.PLAY_PAUSE");
    }

    @Override
    public void nextTrack() {
        runOperaton("com.aimp.service.action.NEXT_TRACK");

    }

    @Override
    public void prevTrack() {
        runOperaton("com.aimp.service.action.PREV_TRACK");
    }

    private void runOperaton(String oper) {
        Intent intent = new Intent();
        String packageName = "com.aimp.player";
        String className = "com.aimp.player.service.AIMPService";
        intent.setClassName(packageName, className);
        intent.setAction(oper);
        App.instance.startService(intent);
    }

}
