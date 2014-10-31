package ru.forpda.example.an21utools;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main","Start application");
        setContentView(R.layout.main);
        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("auto");
        tabSpec.setIndicator("Автозапуск");
        tabSpec.setContent(new Intent(this, AutoRunActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("about");
        tabSpec.setIndicator("О программе");
        tabSpec.setContent(new Intent(this, AboutActivity.class));
        tabHost.addTab(tabSpec);
        Log.d("Main","MainActivity Init...");
    }
}
