package top.moverco.coolmovie.util;

import android.content.Context;
import android.content.Intent;

import java.io.Closeable;
import java.io.IOException;

import top.moverco.coolmovie.MyApplication;
import top.moverco.coolmovie.activity.BaseActivity;
public class UIUtil {
    public static final String TAG = "Cool Movie UIUtil";
    public static Context getContext() {
        return MyApplication.getApplication();
    }

    public static void close(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 页面跳转
     *
     * @param intent
     */
    public static void startActivity(Intent intent) {
        // 如果不在activity里去打开activity  需要指定任务栈  需要设置标签
        if (BaseActivity.activity == null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            BaseActivity.activity.startActivity(intent);
        }
    }


}

