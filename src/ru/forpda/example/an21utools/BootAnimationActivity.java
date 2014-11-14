package ru.forpda.example.an21utools;

import android.app.Activity;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import ru.forpda.example.an21utools.util.SysUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by max on 14.11.2014.
 */
public class BootAnimationActivity extends Activity {
    String[] paths = new String[]{"/system/etc", "/mnt/shared/share/boot"};
    List<BootFile> bootFileList = new ArrayList<BootFile>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bootanimation);
//        ListView listView = (ListView) findViewById(R.id.listviewBootItems);
//        listView.setAdapter(new BootFilesAdapter());

    }

    @Override
    protected void onResume() {
        super.onResume();
        bootFileList.clear();

        for (String path:paths){
            bootFileList.addAll(getFilesFrom(path));
        }
        ListView listView = (ListView) findViewById(R.id.listviewBootItems);
        listView.setAdapter(new BootFilesAdapter());

    }

    /**
     * Считать все фйалы предположительно с бутанимацией )
     * @param path
     * @return
     */
    private ArrayList<BootFile> getFilesFrom(String path){
        List<File> list = SysUtils.getFilesByExtension(path,".zip");
        ArrayList<BootFile> results =  new ArrayList<BootFile>();
        for(File file : list){
            results.add(new BootFile(file.getName(),file.getPath()));
        }
        return results;
    }

    private class BootFilesAdapter extends BaseAdapter {
        public BootFilesAdapter() {
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item;

            if (convertView == null) {
                LayoutInflater ltInflater = getLayoutInflater();
                item  = ltInflater.inflate(R.layout.bootanimation_item,null);
            } else {
                item = convertView;
            }

            BootFile file = bootFileList.get(position);
            ((TextView)item.findViewById(R.id.biFileName)).setText(file.fileName);
            ((TextView)item.findViewById(R.id.biPath)).setText(file.filePath.replace(file.fileName,""));
            return item;
        }


        public final int getCount() {
            return bootFileList.size();
        }

        public final Object getItem(int position) {
            return bootFileList.get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }

    private class BootFile{
        public String fileName;
        public String filePath;

        private BootFile(String fileName, String filePath) {
            this.fileName = fileName;
            this.filePath = filePath;
        }
    }
}