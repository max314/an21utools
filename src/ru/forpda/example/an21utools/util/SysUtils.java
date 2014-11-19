package ru.forpda.example.an21utools.util;

import android.content.Context;
import android.content.Intent;
import android.tw.john.TWUtil;
import android.util.Log;
import ru.forpda.example.an21utools.App;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by max on 31.10.2014.
 * всякие утилитки
 */
public class SysUtils {
    private static LogHelper Log = new LogHelper(SysUtils.class);
    /**
     * Константы для выполнения в твутилитах что значат не известно
     */
    public static final int EXECUTE_COMMAND_AS_0 = 0;
    public static final int EXECUTE_COMMAND_AS_1 = 1;
    public static final int EXECUTE_COMMAND_AS_2 = 2;
    public static final int EXECUTE_COMMAND_AS_3 = 3;
    public static final int EXECUTE_COMMAND_TAG = 40730;

    /**
     * Записать строку как файл
     *
     * @param fileContents строка содержимое
     * @param fileName     имя файла
     */
    public static void writeStringAsFile(final String fileContents, String fileName) {
        Context context = App.instance.getApplicationContext();
        try {
            FileWriter out = new FileWriter(new File(context.getFilesDir(), fileName));
            out.write(fileContents);
            out.close();
        } catch (IOException e) {
            Log.e("Error write string as file", e);
        }
    }

    /**
     * Считать файл как строку
     *
     * @param fileName имя файла
     * @return содержимое
     */
    public static String readFileAsString(String fileName) {
        Context context = App.instance.getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (Exception e) {
            Log.e("Error read file as string", e);
        }
        return stringBuilder.toString();
    }

    /**
     * Запустить активити по имени пакета
     *
     * @param packageName
     */
    public static void runAndroidPackage(String packageName) {
        Intent LaunchIntent = App.instance.getPackageManager().getLaunchIntentForPackage(packageName);
        App.instance.startActivity(LaunchIntent);
    }

    /**
     * Получить список фалов в каталоге с заданным расширением
     *
     * @param path
     * @param ext
     * @return
     */
    public static List<File> getFilesByExtension(String path, final String ext) {
        File[] list = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().toLowerCase().endsWith(ext.toLowerCase()) && file.isFile();
            }
        });
        return Arrays.asList(list);
    }

    /**
     * Получить список файлов из заданого каталога и если в этом каталоге есть подкаталоги то из них,
     * не рекурсивно а только первый уровень подчинения....
     * @param path стартовый путь
     * @param ext расширение
     * @return
     */
    public static List<File> getFilesWithDirByExtension(String path, final String ext, boolean includeSubDir) {
        ArrayList<File> lists  = new ArrayList<File>(); // result
        ArrayList<File> dirs  = new ArrayList<File>(); // dirs
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().toLowerCase().endsWith(ext.toLowerCase()) && file.isFile();
            }
        };

        for (File file : new File(path).listFiles()){
            if (file.isDirectory())
                dirs.add(file);
            if (fileFilter.accept(file))
                lists.add(file);
        }
        if (includeSubDir){
            for (File file : dirs){
                lists.addAll(getFilesWithDirByExtension(file.getAbsolutePath(),ext,false));
            }
        }
        return lists;
    }

    /**
     * Выполнить команду от имени рут
     * @param command
     * @param asLevele
     */
    public static void executeCommand(String command, int asLevele) {
        Log.d(String.format("executeCommand: %s level %d", command, asLevele));
        TWUtil twutil = new TWUtil();
        Log.d("new twutil");
        twutil.open(null);
        Log.d("twutil open");
        try {
            twutil.write(EXECUTE_COMMAND_TAG, asLevele, 0, command);
        } catch (Throwable e) {
            Log.e("twutil error ", e);
        } finally {
            twutil.close();
            Log.d("twutil close");
        }


    }


}
