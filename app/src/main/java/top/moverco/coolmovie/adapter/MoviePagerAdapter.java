package top.moverco.coolmovie.adapter;

import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import top.moverco.coolmovie.R;
import top.moverco.coolmovie.entity.MovieType;
import top.moverco.coolmovie.fragment.CollectedMovieFragment;
import top.moverco.coolmovie.fragment.PopularSortedFragment;
import top.moverco.coolmovie.fragment.RateSortedFragment;
import top.moverco.coolmovie.common.util.LoggerUtil;

/**
 * Created by liuzongxiang on 18/05/2017.
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {
    public enum Tab {
        TOP_RATE(R.string.top_rate),
        POP(R.string.popularity),
        COLLECTION(R.string.collection);

        private final int mStringResource;

        Tab(@StringRes int stringResource) {
            mStringResource = stringResource;
        }

        public int getStringResource() {
            return mStringResource;
        }
    }

    private final Tab[] mtabs = Tab.values();
    private final CharSequence[] mTitles = new CharSequence[mtabs.length];
    private final MovieType mMovieType;
    private final MovieType[] mMovieTypes = MovieType.values();

    public MoviePagerAdapter(FragmentManager fm, Resources resources, MovieType movieType) {
        super(fm);
        mMovieType = movieType;
        for (int i = 0; i < mtabs.length; i++) {
            mTitles[i] = resources.getString(mtabs[i].getStringResource());
            LoggerUtil.debug("TAB Title:"+mTitles[i]);
        }

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoggerUtil.debug("TAB1");
                return new RateSortedFragment();
            case 1:
                LoggerUtil.debug("TAB2");
                return new PopularSortedFragment();
            case 2:
                LoggerUtil.debug("TAB3");
                return new CollectedMovieFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mtabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public long getItemId(int position) {
        for (int i = 0; i < mMovieTypes.length; i++) {
            if (mMovieTypes[i] == mMovieType) {
                return (i * 10) + position;
            }
        }
        throw new IllegalArgumentException("Invalid position:["+position+"]or movie type:["+mMovieType+"]");
    }

}
