package ru.forpda.example.an21utools;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.*;
import ru.forpda.example.an21utools.model.AppInfo;
import ru.forpda.example.an21utools.model.AutoRunModel;
import ru.forpda.example.an21utools.model.IndentActivityCodes;
import ru.forpda.example.an21utools.model.ModelFactory;
import ru.forpda.example.an21utools.util.LogHelper;
import ru.forpda.example.an21utools.util.SysUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Активити для запускаемых приложений
 * Created by max on 28.10.2014.
 */
public class AutoRunActivity extends FragmentActivity implements Observer {

    private static LogHelper Log = new LogHelper(AutoRunActivity.class);

    private final int CM_DELETE_ID = 1;
    private AutoRunModel model;
    private Switch switchAutoRun;
    private Button buAddAplication;
    private ListView listviewAutoRun;
    private AppInfoAdapter appInfoAdapter;

    /**
     * Флаг указывающий что модель считываеться и в этот момент ненадо обрабатывать листененры привет андройдику
     */
    private boolean insideReadFromModel = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate enter");
        super.onCreate(savedInstanceState);

        model = ModelFactory.getAutoRunModel();
        model.addObserver(this);

        setContentView(R.layout.autorun);
        // Чекалка запускаемся или нет
        switchAutoRun = (Switch) findViewById(R.id.switchAutoRun);
        switchAutoRun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!insideReadFromModel)
                    model.setStarting(!model.isStarting());
            }
        });
        // кнопарь добавления
        buAddAplication = (Button) findViewById(R.id.buttonTest);
        buAddAplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AutoRunActivity.this, SelectApplicationActivity.class);
                startActivityForResult(intent, IndentActivityCodes.SELECT_APPLICATION_CODE);

            }
        });
        // листвью
        listviewAutoRun = (ListView) findViewById(R.id.listviewAutoRun);
        appInfoAdapter = new AppInfoAdapter();
        listviewAutoRun.setAdapter(appInfoAdapter);
        registerForContextMenu(listviewAutoRun);
//        listviewAutoRun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String packageName = model.getAppInfoList().get(i).getName();
//                SysUtils.runAndroidPackage(packageName);
//            }
//        });
        readFromModel();
        Log.d("onCreate leave");
    }

    @Override
    public void update(Observable observable, Object o) {
        readFromModel();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            // Дряпнем элемент
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            model.removeAppinfo(position);
        }
        return super.onContextItemSelected(item);
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
        insideReadFromModel = true;
        try {
            switchAutoRun.setChecked(model.isStarting());
            appInfoAdapter.notifyDataSetChanged();
        } finally {
            insideReadFromModel = false;
        }
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
                Log.e("Error in display package",e);
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