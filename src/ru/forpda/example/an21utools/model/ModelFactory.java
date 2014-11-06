package ru.forpda.example.an21utools.model;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.forpda.example.an21utools.App;
import ru.forpda.example.an21utools.util.SysUtils;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by max on 29.10.2014.
 */
public final class ModelFactory {

    private static final String AUTORUN_MODEL="AutoRun";
    private static AutoRunModel model;
    private static final String AUTORUN_FILENAME ="autorun.txt";

    // Прогрузим модельки если есть ))
    static {
        try {
            loadAutoRunModel();
        } catch (Exception e) {
            e.printStackTrace();
            // Создаем пустую модель
            AutoRunModel model = new AutoRunModel();
            model.setStarting(false); // не запускать
            model.setAppInfoList(new ArrayList<AppInfo>());
            ModelFactory.model = model;
        }
    }

    /**
     * @return AutoRun model for application
     */
    public static AutoRunModel getAutoRunModel(){
        return ModelFactory.model;
    }


    /**
     * Save model to file in json format
     */
    public static void saveAutoRunModel(){
        Log.d("saveAutoRunModel","start");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(AUTORUN_MODEL, AUTORUN_MODEL);
            jsonObject.put("starting", model.isStarting());
            jsonObject.put("startdelay", model.getStartDelay());
            jsonObject.put("applicationdelay", model.getApplicationDelay());
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < model.getAppInfoList().size(); i++) {
                JSONObject item = new JSONObject();
                item.put("name",model.getAppInfoList().get(i).getName());
                jsonArray.put(item);
            }
            jsonObject.put("appinfo",jsonArray);
            String buffer = jsonObject.toString(4);
            SysUtils.writeStringAsFile(buffer, AUTORUN_FILENAME);
            Log.d("saveAutoRunModel",buffer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load model from json format
     * used internal
     * may be raise exception
     */
    private static void loadAutoRunModel(){
        Log.d("loadAutoRunModel","start");
        try {
            String buffer = SysUtils.readFileAsString(AUTORUN_FILENAME);
            JSONObject jsonObject = new JSONObject(buffer);
            AutoRunModel m = new AutoRunModel();
            m.setAppInfoList(new ArrayList<AppInfo>());
            m.getAppInfoList().clear();
            m.setStarting(jsonObject.getBoolean("starting"));
            m.setStartDelay(jsonObject.optInt("startdelay",5));
            m.setApplicationDelay(jsonObject.optInt("applicationdelay",5));
            JSONArray jsonArray = jsonObject.getJSONArray("appinfo");
            for (int i = 0; i < jsonArray.length(); i++) {
                m.addAppinfo(jsonArray.getJSONObject(i).getString("name"));
            }
            model=m;
            Log.d("loadAutoRunModel","ok");
        } catch (JSONException e) {
            Log.e("loadAutoRunModel","Error",e);
            e.printStackTrace();
            throw new RuntimeException("Cannot load autorun model");
        }
    }


}
