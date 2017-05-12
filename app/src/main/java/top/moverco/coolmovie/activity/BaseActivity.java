package top.moverco.coolmovie.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import top.moverco.coolmovie.MyApplication;
import top.moverco.coolmovie.util.UIUtil;

public class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        activity = this;
        ((MyApplication) UIUtil.getContext()).addActivity(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    private void init() {
        initData();
        initEvents();
    }

    /***
     * 初始化事件（监听事件等事件绑定）
     */
    protected void initEvents() {
    }

    /**
     * 绑定数据
     */
    protected void initData() {
    }

    /**
     * activity退出时将activity移出栈
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyApplication) UIUtil.getContext()).removeActivity(this);
    }
}
