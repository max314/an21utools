package ru.forpda.example.an21utools.music;

import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;
import ru.forpda.example.an21utools.App;
import ru.forpda.example.an21utools.util.DisplayToast;

/**
 * Created by max on 11.11.2014.
 */
public class UniversalMusicOperation implements IMusicOpertion {

    Handler handler;

    public UniversalMusicOperation() {
        handler = new Handler();
    }

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

    private void runOperaton(int oper) {
        sendToast(oper);
        Intent mbIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
        //KeyEvent
        KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, oper);
        //mbIntent
        mbIntent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
        //China_MBReceiverEngland_MBReceiver
        App.instance.sendBroadcast(mbIntent);
    }

    private void sendToast(int oper) {
        String operStr = "operation not detected";
        switch (oper) {
            case KeyEvent.KEYCODE_MEDIA_PLAY:
                operStr = "KEYCODE_MEDIA_PLAY";
                break;
            case KeyEvent.KEYCODE_MEDIA_PAUSE:
                operStr = "KEYCODE_MEDIA_PAUSE";
                break;
            case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                operStr = "KEYCODE_MEDIA_PLAY_PAUSE";
                break;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                operStr = "KEYCODE_MEDIA_NEXT";
                break;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                operStr = "KEYCODE_MEDIA_PREVIOUS";
                break;
        }
        operStr = String.format("Сформирован бродкаст с прарметром (%s)", operStr);
        handler.post(new DisplayToast(App.instance, operStr));
    }

}
