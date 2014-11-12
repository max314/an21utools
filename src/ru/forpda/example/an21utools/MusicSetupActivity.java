package ru.forpda.example.an21utools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import ru.forpda.example.an21utools.model.AutoRunModel;
import ru.forpda.example.an21utools.model.ModelFactory;
import ru.forpda.example.an21utools.music.UniversalMusicOperation;
import ru.forpda.example.an21utools.util.LogHelper;

/** TODO прикрутить модель как следует
 * Created by max on 11.11.2014.
 */
public class MusicSetupActivity extends Activity {
    private static LogHelper Log = new LogHelper(MainActivity.class);
    Spinner spinnerProvider;
    Switch switchmusicWidgetToast;
    AutoRunModel model;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ModelFactory.getAutoRunModel();
        setContentView(R.layout.musicsetup);

        ((Button) findViewById(R.id.play)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               model.getMusicOperation().play();
            }
        });
        ((Button) findViewById(R.id.pause)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.getMusicOperation().pause();
            }
        });
        ((Button) findViewById(R.id.playpause)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.getMusicOperation().playPause();
            }
        });
        ((Button) findViewById(R.id.prev)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.getMusicOperation().prevTrack();
            }
        });
        ((Button) findViewById(R.id.next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.getMusicOperation().nextTrack();
            }
        });
        spinnerProvider = (Spinner) findViewById(R.id.musicsetupSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, model.getMusicProviders());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvider.setAdapter(adapter);
        spinnerProvider.setSelection(model.getMusicProvederIndex());
        spinnerProvider.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                model.setMusicProvederIndex(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        switchmusicWidgetToast = (Switch) findViewById(R.id.switchmusicWidgetToast);
        switchmusicWidgetToast.setChecked(model.isMusicWidgetToast());
        switchmusicWidgetToast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                model.setMusicWidgetToast(b);
            }
        });
    }
}