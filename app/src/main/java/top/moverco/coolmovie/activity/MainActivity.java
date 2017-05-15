package top.moverco.coolmovie.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import top.moverco.coolmovie.R;
import top.moverco.coolmovie.fragment.PopularSortedFragment;
import top.moverco.coolmovie.fragment.RateSortedFragment;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;
import top.moverco.coolmovie.util.NetWorkUtil;

import static top.moverco.coolmovie.util.MovieURLUtil.GET_TOP_RATED_ROOT_URL;

public class MainActivity extends AppCompatActivity {
    private FrameLayout noNetworkFrame;
    private FrameLayout listFrame;
    public static Fragment currentFrament = null;
    private RateSortedFragment mRateSortedFragment;
    private PopularSortedFragment mPopularSortedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
        initViews();
        checkIfHasNetWork();
        currentFrament = mPopularSortedFragment;
        switchToFragment(mRateSortedFragment);
    }


    void initViews() {
        noNetworkFrame = (FrameLayout) findViewById(R.id.no_network_view);
        listFrame = (FrameLayout) findViewById(R.id.list_frame);
        mPopularSortedFragment = new PopularSortedFragment();
        mRateSortedFragment = new RateSortedFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_rate:
                checkIfHasNetWork();
                cancelLoad(MovieURLUtil.GET_POPULAR_URL);
                switchToFragment(mRateSortedFragment);
                return true;
            case R.id.sort_by_popular:
                checkIfHasNetWork();
                cancelLoad(GET_TOP_RATED_ROOT_URL);
                switchToFragment(mPopularSortedFragment);
                return true;
            case R.id.refresh:
                if (currentFrament==mRateSortedFragment){
                    mRateSortedFragment.refreshMovies();
                }else if (currentFrament==mPopularSortedFragment){
                    mPopularSortedFragment.refreshMovies();
                }
                Toast.makeText(MainActivity.this,"refresh",Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    void checkIfHasNetWork() {
        if (!NetWorkUtil.isNetWorkAvailable(this)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                listFrame.setVisibility(View.GONE);
                noNetworkFrame.setVisibility(View.VISIBLE);
            }
        } else {
            listFrame.setVisibility(View.VISIBLE);
            noNetworkFrame.setVisibility(View.GONE);
        }
    }


    private void switchToFragment(Fragment fragment) {
        if (currentFrament == fragment)
            return;
        LoggerUtil.debug("switch to fragment");
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.list_frame, fragment);
        currentFrament = fragment;
        transaction.commit();
        LoggerUtil.debug("switch to fragment end");
    }


    private void cancelLoad(String url) {
        RequestCall call = OkHttpUtils.get()
                .url(url)
                .build();
        if (call != null)
            call.cancel();
    }


}
