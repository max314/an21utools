package ru.forpda.example.an21utools;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.forpda.example.an21utools.util.DisplayToast;
import ru.forpda.example.an21utools.util.LogHelper;
import ru.forpda.example.an21utools.util.StringUtils;
import ru.forpda.example.an21utools.util.SysUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by max on 14.11.2014.
 */
public class BootAnimationActivity extends Activity {
    private static LogHelper Log = new LogHelper(BootAnimationActivity.class);
    String[] paths = new String[]{"/system/etc/bootanimation", "mnt/store/usb1","mnt/store/usb2","mnt/store/usb-1","/extsd","/sdcard","mnt/store/usb-2","/mnt/shared/share/boot"};
    List<BootFile> bootFileList = new ArrayList<BootFile>();
    ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bootanimation);

        listView = (ListView) findViewById(R.id.listviewBootItems);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BootFile bootFile = bootFileList.get(i);
                executeCreateBoot(bootFile);
            }
        });
    }

    private void executeCreateBoot(BootFile bootFile) {
        try {
            if (!StringUtils.isEmpty(bootFile.fullFileName)){ // deafult
                String cmd = String.format("busybox cp \"%s\" /data/local/bootanimation.zip", bootFile.fullFileName, bootFile.fullFileName);
                SysUtils.executeCommand(cmd,SysUtils.EXECUTE_COMMAND_AS_0);
                SysUtils.executeCommand("chmod 755 /data/local/bootanimation.zip",SysUtils.EXECUTE_COMMAND_AS_2);
            }
            else {
                SysUtils.executeCommand("rm /data/local/bootanimation.zip",SysUtils.EXECUTE_COMMAND_AS_2);
            }
        } catch (Throwable e) {
            Log.e("Error copy boot animation file",e);
            new DisplayToast(this, "Ошибка установки бут анимации").run();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();

    }

    private void loadData() {
        bootFileList.clear();
        bootFileList.add(new BootFile("По умолчанию",null));
        for (String path:paths){
            bootFileList.addAll(getFilesFrom(path));
        }
        listView.setAdapter(new BootFilesAdapter());
    }

    /**
     * Считать все фйалы предположительно с бутанимацией )
     * @param path
     * @return
     */
    private ArrayList<BootFile> getFilesFrom(String path){
        ArrayList<BootFile> results =  new ArrayList<BootFile>();
        try {
            List<File> list = SysUtils.getFilesByExtension(path,".zip");
            for(File file : list){
                results.add(new BootFile(file.getName(),file.getPath()));
            }
        } catch (Throwable e) {
            Log.e("Error load from path "+path,e);
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
            String path="";
            if (!StringUtils.isEmpty(file.fullFileName)){
                path = file.fullFileName.replace(file.fileName,"");
            }
            ((TextView)item.findViewById(R.id.biPath)).setText(path);
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
        public String fullFileName;

        private BootFile(String fileName, String filePath) {
            this.fileName = fileName;
            this.fullFileName = filePath;
        }
    }
}