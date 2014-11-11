package ru.forpda.example.an21utools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import ru.forpda.example.an21utools.music.AimpMusicOperation;
import ru.forpda.example.an21utools.music.IMusicOpertion;
import ru.forpda.example.an21utools.music.UniversalMusicOperation;
import ru.forpda.example.an21utools.util.LogHelper;

/**
 * Created by max on 11.11.2014.
 */
public class MusicSetupActivity extends Activity {
    private static LogHelper Log = new LogHelper(MainActivity.class);
    Button musicSetupTestButton;
    IMusicOpertion musicOpertion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicOpertion = new AimpMusicOperation();
        setContentView(R.layout.musicsetup);
        musicSetupTestButton = (Button) findViewById(R.id.musicSetupTestButton);
        musicSetupTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new UniversalMusicOperation().playPause();
                    return;

//                    Intent intent = new Intent();
//                    String packageName = "com.aimp.player";
//                    String className = "com.aimp.player.service.AIMPService";
//                    intent.setClassName(packageName, className);
//                    intent.setAction("com.aimp.service.action.PLAY_PAUSE");
//                    startService(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ((Button) findViewById(R.id.play)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicOpertion.play();
            }
        });
        ((Button) findViewById(R.id.pause)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicOpertion.pause();
            }
        });
        ((Button) findViewById(R.id.playpause)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicOpertion.playPause();
            }
        });
        ((Button) findViewById(R.id.prev)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicOpertion.prevTrack();
            }
        });
        ((Button) findViewById(R.id.next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicOpertion.nextTrack();
            }
        });
    }
}