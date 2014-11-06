package ru.forpda.example.an21utools.model;

import java.util.List;
import java.util.Observable;

/**
 * Created by max on 28.10.2014.
 */
public class AutoRunModel extends Observable {
    /// Стартовать при запуске
    private boolean starting;
    /*
        Задержка после старта сервиса
     */
    private int startDelay = 5;
    /*
       Задержка после старта приложения
     */
    private int applicationDelay = 3;

    private List<AppInfo> appInfoList;

    /**
     * Запускать при старте
     * @return
     */
    public boolean isStarting() {
        return starting;
    }

    /**
     * Установить признак запуска при старте
     * @param starting
     */
    public void setStarting(boolean starting) {
        this.starting = starting;
        setChanged();
        this.notifyObservers();
    }

    /**
     * получить Список приложений автозапуска
     * @return
     */
    public List<AppInfo> getAppInfoList() {
        return appInfoList;
    }

    /***
     * установить Список приложений автозапуска
     * @param appInfoList
     */
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

    /**
     * Задержка в секундах при запуске сервиса
     * @return
     */
    public int getStartDelay() {
        return startDelay;
    }

    /**
     * установить задержку в секундах при запуске сервиса
     * @param startDelay
     */
    public void setStartDelay(int startDelay) {
        this.startDelay = startDelay;
    }

    /***
     * Задержка в секундах между стартом приложений
     * @return
     */
    public int getApplicationDelay() {
        return applicationDelay;
    }

    /**
     * установить задержку в секундах между стартом приложений
     * @param applicationDelay
     */
    public void setApplicationDelay(int applicationDelay) {
        this.applicationDelay = applicationDelay;
    }
}
