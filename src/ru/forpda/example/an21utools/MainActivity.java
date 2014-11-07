package ru.forpda.example.an21utools;



import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;


import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import ru.forpda.example.an21utools.util.LogHelper;

public class MainActivity extends FragmentActivity  {
    private static LogHelper Log = new LogHelper(MainActivity.class);
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Start application");
        setContentView(R.layout.main);

        try {
            FragmentTabHost mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
            mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

            mTabHost.addTab(mTabHost.newTabSpec("auto").setIndicator("Автозапуск"), AutoRunActivity.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("about").setIndicator("О программе"), AboutActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        TabHost.TabSpec tabSpec;
//
//        tabSpec = tabHost.newTabSpec("auto");
//        tabSpec.setIndicator("Автозапуск");
//        tabSpec.setContent(new Intent(this, AutoRunActivity.class));
//        tabHost.addTab(tabSpec);
//
//        tabSpec = tabHost.newTabSpec("about");
//        tabSpec.setIndicator("О программе");
//        tabSpec.setContent(new Intent(this, AboutActivity.class));
//        tabHost.addTab(tabSpec);
        Log.d("MainActivity Init...");
    }
}
