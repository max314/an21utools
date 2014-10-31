package ru.forpda.example.an21utools;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.forpda.example.an21utools.model.AppInfo;
import ru.forpda.example.an21utools.model.AutoRunModel;
import ru.forpda.example.an21utools.model.IndentActivityCodes;
import ru.forpda.example.an21utools.model.ModelFactory;
import ru.forpda.example.an21utools.util.SysUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by max on 28.10.2014.
 */
public class AutoRunActivity extends Activity implements Observer {

    @Override
    public void update(Observable observable, Object o) {
        readFromModel();
    }

    private AutoRunModel model;
    private Switch switchAutoRun;
    private Button buAddAplication;
    private ListView listviewAutoRun;
    private AppInfoAdapter appInfoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ModelFactory.getAutoRunModel();
        model.addObserver(this);

        setContentView(R.layout.autorun);

        switchAutoRun = (Switch) findViewById(R.id.switchAutoRun);
        switchAutoRun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                model.setStarting(!model.isStarting());
            }
        });
        buAddAplication = (Button) findViewById(R.id.buttonTest);
        buAddAplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AutoRunActivity.this, SelectApplicationActivity.class);
                startActivityForResult(intent, IndentActivityCodes.SELECT_APPLICATION_CODE);

            }
        });

        listviewAutoRun = (ListView) findViewById(R.id.listviewAutoRun);
        appInfoAdapter = new AppInfoAdapter();
        listviewAutoRun.setAdapter(appInfoAdapter);
        listviewAutoRun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String packageName = model.getAppInfoList().get(i).getName();
                SysUtils.runAndroidPackage(packageName);
            }
        });
        readFromModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.deleteObserver(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ModelFactory.saveAutoRunModel();

    }

    public void readFromModel() {
        switchAutoRun.setChecked(model.isStarting());
        appInfoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IndentActivityCodes.SELECT_APPLICATION_CODE) {
            String str = data.getStringExtra(IndentActivityCodes.SELECT_APPLICATION_KEY);
            if (!str.isEmpty()) {
                Toast.makeText(this, "You selected: " + str, Toast.LENGTH_SHORT).show();
                model.addAppinfo(str);

            }
        }
    }

    public class AppInfoAdapter extends BaseAdapter {
        public AppInfoAdapter() {
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item;

            if (convertView == null) {
                LayoutInflater ltInflater = getLayoutInflater();
                item = ltInflater.inflate(R.layout.select_application_item, null);
            } else {
                item = convertView;
            }

            AppInfo appInfo = model.getAppInfoList().get(position);
            try {
                ApplicationInfo info = getPackageManager().getApplicationInfo(appInfo.getName(), 0);
                ((ImageView) item.findViewById(R.id.imageView)).setImageDrawable(info.loadIcon(getPackageManager()));
                ((TextView) item.findViewById(R.id.appName)).setText(info.loadLabel(getPackageManager()));
                ((TextView) item.findViewById(R.id.appVersion)).setText(info.packageName);
//                ((TextView)item.findViewById(R.id.appVersion)).setText(info.serviceInfo.applicationInfo.loadDescription(getPackageManager()));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                ((TextView) item.findViewById(R.id.appName)).setText("Package " + appInfo.getName() + " not found!!!!");
            }
            return item;
        }


        public final int getCount() {
            return model.getAppInfoList().size();
        }

        public final Object getItem(int position) {
            return model.getAppInfoList().get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }

}