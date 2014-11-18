package ru.forpda.example.an21utools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android.internal.util.ArrayUtils;
import ru.forpda.example.an21utools.model.ModelFactory;
import ru.forpda.example.an21utools.model.ShortCutModel;
import ru.forpda.example.an21utools.util.ICommand;

/**
 * Created by max on 17.11.2014.
 */
public class Shortcuts extends Activity {

    ShortCutModel.ShortCutItem[] data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ShortCutModel().getData();

        final Intent intent = getIntent();
        final String action = intent.getAction();

        // If the intent is a request to create a shortcut, we'll do that and exit
        if (Intent.ACTION_CREATE_SHORTCUT.equals(action)) {
            setupShortcut();
        }
        setResult(RESULT_CANCELED, intent);

    }

    private void processShortcut(Intent intent) {
    }

    private void setupShortcut() {
        setContentView(R.layout.shortcuts);


        ListView listView = (ListView) findViewById(R.id.shortcutListView);
        listView.setAdapter(new ArrayAdapter<ShortCutModel.ShortCutItem>(this, android.R.layout.simple_list_item_1, data));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShortCutModel.ShortCutItem item = data[i];

                final Intent shortcutIntent = new Intent(Shortcuts.this, ShortCutExecute.class);
                shortcutIntent.setAction(item.getAction());
                final Intent.ShortcutIconResource iconResource = Intent.ShortcutIconResource.fromContext(Shortcuts.this, item.getResourceId());
                final Intent intent = new Intent();
                intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, item.getName());
                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
                intent.setAction(item.getAction());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}


