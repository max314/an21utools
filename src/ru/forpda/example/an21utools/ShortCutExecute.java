package ru.forpda.example.an21utools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import ru.forpda.example.an21utools.model.ShortCutModel;

/**
 * Created by max on 18.11.2014.
 */
public class ShortCutExecute extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
        final String action = intent.getAction();

        String intentAction = intent.getAction();
        for (ShortCutModel.ShortCutItem item : new ShortCutModel().getData()){
            if (item.getAction().equals(intentAction)){
                // нашли
                item.exec();
                break;
            }
        }
        finish();
    }

}
