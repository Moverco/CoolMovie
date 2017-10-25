package top.moverco.coolmovie.common.util;

import java.io.Closeable;
import java.io.IOException;

public class UIUtil {
    public static final String TAG = "Cool Movie UIUtil";

    public static void close(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

