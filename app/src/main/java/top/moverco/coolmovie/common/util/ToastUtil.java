package top.moverco.coolmovie.common.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Moverco.
 */

public class ToastUtil {
    public static void showShortToast(Context context,String msg){
        showToast(context,msg,Toast.LENGTH_SHORT);
    }
    public static void showLongToast(Context context,String msg){
        showToast(context,msg,Toast.LENGTH_LONG);
    }
    private static void showToast(Context context,String msg,int time){
        Toast.makeText(context,msg,time).show();
    }
}
