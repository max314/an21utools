package ru.forpda.example.an21utools.model;

import java.util.List;
import java.util.Observable;

/**
 * Created by max on 28.10.2014.
 */
public class AutoRunModel extends Observable {
    /// Стартовать при запуске
    private boolean starting;
    private List<AppInfo> appInfoList;

    public boolean isStarting() {
        return starting;
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
        setChanged();
        this.notifyObservers();
    }

    public List<AppInfo> getAppInfoList() {
        return appInfoList;
    }

    public void setAppInfoList(List<AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }

    /**
     * Добавить приложение
     * @param name
     */
    public void addAppinfo(String name){
        for (int i = 0; i < appInfoList.size(); i++) {
            if (appInfoList.get(i).getName().compareTo(name)==0)
                return;
        }
        this.appInfoList.add(new AppInfo(name));
        setChanged();
        this.notifyObservers();
    }

    /**
     * Удалить приложение
     * @param index
     */
    public void removeAppinfo(int index){
        this.appInfoList.remove(index);
        setChanged();
        this.notifyObservers();
    }
}
