package top.moverco.coolmovie.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import top.moverco.coolmovie.R;
import top.moverco.coolmovie.adapter.MoviePagerAdapter;
import top.moverco.coolmovie.entity.MovieType;
import top.moverco.coolmovie.fragment.CollectedMovieFragment;
import top.moverco.coolmovie.fragment.PopularSortedFragment;
import top.moverco.coolmovie.fragment.RateSortedFragment;
import top.moverco.coolmovie.fragment.Refreshed;
import top.moverco.coolmovie.common.util.LoggerUtil;

import static top.moverco.coolmovie.R.id.refresh;

public class MainActivity extends AppCompatActivity {


    private int mCurrentNavPosition = -1;
    private static final int RATESORTED_FRAGMENT = 0;
    private static final int POPULARSORTED_FRAGMENT = 1;
    private static final int COLLECTEDMOVIE_FRAGMENT = 2;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private MovieType[] mMovieTypes = MovieType.values();
    private static final String SELECTED_POSITION = "SELECTED_POSITION";
    private RateSortedFragment mRateSortedFragment;
    private PopularSortedFragment mPopularSortedFragment;
    private CollectedMovieFragment mCollectedMovieFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        initViews(savedInstanceState);
        initEvents();
        if (savedInstanceState == null) {
            setupTabs(1);
        }
    }


    void initViews(final Bundle savedInstanceState) {
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.mipmap.category);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_rate:
                        mCurrentNavPosition = 0;
                        break;
                    case R.id.nav_pop:
                        mCurrentNavPosition = 1;
                        break;
                    case R.id.nav_collection:
                        mCurrentNavPosition = 2;
                        break;
                    default:
                        LoggerUtil.debug("Unknow drawer selected.");
                }
                item.setChecked(true);
                return true;
            }
        });
    }

    void initEvents() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case refresh:
                refreshCurrentFrament();
                return true;
        }
        return false;
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentNavPosition = savedInstanceState.getInt(SELECTED_POSITION, 0);
        final Menu menu = mNavigationView.getMenu();
        final MenuItem menuItem = menu.getItem(mCurrentNavPosition);
        menuItem.setChecked(true);
        setupTabs(mCurrentNavPosition);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_POSITION, mCurrentNavPosition);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_rate:
                mCurrentNavPosition = 0;
                break;
            case R.id.nav_pop:
                mCurrentNavPosition = 1;
                break;
            case R.id.nav_collection:
                mCurrentNavPosition = 2;
                break;
            default:
                LoggerUtil.debug("Unknow drawer selected.");
        }
        item.setChecked(true);
        setupTabs(mCurrentNavPosition);
        LoggerUtil.debug("Navi position:" + mCurrentNavPosition);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private Refreshed getCurrentFragment() {
        if (!(mCurrentNavPosition != -1)) {
            Refreshed refreshed;
            switch (mCurrentNavPosition){
                case RATESORTED_FRAGMENT:
                    refreshed = RateSortedFragment.getInstance();
                    break;
                case POPULARSORTED_FRAGMENT:
                    refreshed = PopularSortedFragment.getInstance();
                    break;
                case COLLECTEDMOVIE_FRAGMENT:
                    refreshed = CollectedMovieFragment.getInstance();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown mCurrentNavPosition : "+mCurrentNavPosition);
            }
            return refreshed;
        }
        else throw new IllegalArgumentException(" mCurrentNavPosition  is -1 ");
    }

    private void refreshCurrentFrament() {
        Refreshed refreshed = getCurrentFragment();
        refreshed.refresh();
    }

    private void setupTabs(int position) {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        final MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager(), getResources(), mMovieTypes[position]);

        LoggerUtil.debug("movie type:" + mMovieTypes[position] + ",position:" + position + ",mcurrent:" + mCurrentNavPosition);
        tabLayout.removeAllTabs();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // TODO: 18/05/2017
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                LoggerUtil.debug("SET current :" + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mCurrentNavPosition = position;
    }


}
