package ru.forpda.example.an21utools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by max on 17.11.2014.
 */
public class Shortcuts extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
        final String action = intent.getAction();

        // If the intent is a request to create a shortcut, we'll do that and exit
        if (Intent.ACTION_CREATE_SHORTCUT.equals(action)) {
            setupShortcut();
        }
        else {
            processShortcut(intent);
        }
        finish();
        return;


    }

    private void processShortcut(Intent intent) {

    }

    private void setupShortcut() {
        setContentView(R.layout.shortcuts);
        ShortCutItem[] data = new ShortCutItem[]{
                new ShortCutItem("Предыдущий трек","ru.forpda.example.an21utools.prev",R.drawable.media_skip_backward),
                new ShortCutItem("Играть","ru.forpda.example.an21utools.play",R.drawable.media_playback_start),
                new ShortCutItem("Пауза","ru.forpda.example.an21utools.pause",R.drawable.media_playback_pause),
                new ShortCutItem("Играть/Пауза","ru.forpda.example.an21utools.playpause",R.drawable.media_play_pause),
                new ShortCutItem("Следующий трек","ru.forpda.example.an21utools.next",R.drawable.media_skip_forward),
        };
        ListView listView = (ListView) findViewById(R.id.shortcutListView);

    }

    private class ShortCutItem{
        String Name;
        String Action;
        int ResourceId;

        private ShortCutItem(String name, String action, int resourceId) {
            Name = name;
            Action = action;
            ResourceId = resourceId;
        }
    }
}