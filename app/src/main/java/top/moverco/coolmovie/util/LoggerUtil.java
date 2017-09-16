package top.moverco.coolmovie.util;

import android.util.Log;

import top.moverco.coolmovie.BuildConfig;

/**
 * Created by Moverco
 */

public class LoggerUtil {
    public static String TAG = "Cool Movie";
    public static void debug(String msg){
        if (BuildConfig.OPEN_LOG)
        Log.d(TAG,msg);
    }
}
