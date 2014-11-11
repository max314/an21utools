package ru.forpda.example.an21utools.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by max on 11.11.2014.
 */
public class DisplayToast  implements Runnable {

    private final Context mContext;
    String mText;

    public DisplayToast(Context mContext, String text){
        this.mContext = mContext;
        mText = text;
    }

    public void run(){
        Toast.makeText(mContext, mText, Toast.LENGTH_SHORT).show();
    }
}
