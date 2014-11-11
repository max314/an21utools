package ru.forpda.example.an21utools.music;

import android.content.Intent;
import android.view.KeyEvent;
import ru.forpda.example.an21utools.App;

/**
 * Created by max on 11.11.2014.
 */
public class UniversalMusicOperation implements IMusicOpertion {

    @Override
    public void play() {
        runOperaton(KeyEvent.KEYCODE_MEDIA_PLAY);
    }

    @Override
    public void pause() {
        runOperaton(KeyEvent.KEYCODE_MEDIA_PAUSE);
    }

    @Override
    public void playPause() {
        runOperaton(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
    }

    @Override
    public void nextTrack() {
        runOperaton(KeyEvent.KEYCODE_MEDIA_NEXT);
    }

    @Override
    public void prevTrack() {
        runOperaton(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
    }

    private void runOperaton(int oper){
        Intent mbIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
        //KeyEvent
        KeyEvent keyEvent = new KeyEvent (KeyEvent.ACTION_DOWN,oper) ;
        //mbIntent
        mbIntent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
        //China_MBReceiverEngland_MBReceiver
        App.instance.sendBroadcast(mbIntent);
    }
}
