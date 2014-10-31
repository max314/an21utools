package ru.forpda.example.an21utools.model;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.forpda.example.an21utools.App;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by max on 29.10.2014.
 */
public final class ModelFactory {

    static final String AUTORUN_MODEL="AutoRun";
    static AutoRunModel model;

    public static AutoRunModel getAutoRunModel(){

        if (ModelFactory.model==null) {
            AutoRunModel model = new AutoRunModel();
            model.setAppInfoList(new ArrayList<AppInfo>());
            model.getAppInfoList().add(new AppInfo("com.android.browser"));
            model.getAppInfoList().add(new AppInfo("com.anddroid.gallery"));
            model.getAppInfoList().add(new AppInfo("com.aimp.player"));
            ModelFactory.model = model;
        }
        return ModelFactory.model;
    }

    public static void SaveAutoRunModel(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(AUTORUN_MODEL, AUTORUN_MODEL.toString());
            jsonObject.put("starting", model.isStarting());
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < model.getAppInfoList().size(); i++) {
                JSONObject item = new JSONObject();
                item.put("name",model.getAppInfoList().get(i).getName());
                jsonArray.put(item);
            }
            jsonObject.put("appinfo",jsonArray);
            String buffer = jsonObject.toString(4);
            writeStringAsFile(buffer,"autorun.txt");
            Log.d("SaveAutoRunModel",buffer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void writeStringAsFile(final String fileContents, String fileName) {
        Context context = App.instance.getApplicationContext();
        try {
            FileWriter out = new FileWriter(new File(context.getFilesDir(), fileName));
            out.write(fileContents);
            out.close();
        } catch (IOException e) {
            Log.e("Error","Error", e);
        }
    }

    public static String readFileAsString(String fileName) {
        Context context = App.instance.getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (FileNotFoundException e) {
            Log.e("Error","Error", e);

        } catch (IOException e) {
            Log.e("Error","Error", e);
        }

        return stringBuilder.toString();
    }
}
