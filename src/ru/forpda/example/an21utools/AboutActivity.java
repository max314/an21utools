package ru.forpda.example.an21utools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.stericson.RootTools.RootTools;

/**
 * Created by max on 28.10.2014.
 */
public class AboutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Button btn = (Button) findViewById(R.id.AboutTest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BackgroudService.class);
                startService(intent);
            }
        });
        TextView tv = (TextView) findViewById(R.id.textViewDebug);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Версия приложения = %s \n", App.instance.getString(R.string.app_version)));
        stringBuilder.append(String.format("Помидоры можно прислать на max314.an21u@gmail.com \n\n"));
        stringBuilder.append(String.format("Побочные эффекты ------------------------\n"));
        stringBuilder.append(String.format("Root доступен = %s \n", RootTools.isRootAvailable()));
        stringBuilder.append(String.format("Busybox доступен = %s \n", RootTools.isBusyboxAvailable()));
        tv.setText(stringBuilder.toString());

    }
}