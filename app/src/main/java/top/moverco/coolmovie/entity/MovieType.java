package top.moverco.coolmovie.entity;

import android.support.annotation.StringRes;

import top.moverco.coolmovie.R;

/**
 * Created by liuzongxiang on 18/05/2017.
 */

public enum MovieType {
    TOP_RATE(R.string.top_rate,R.string.rate_description),
    POP(R.string.popularity,R.string.pop_description),
    COLLECTION(R.string.collection,R.string.collection_description);

    private final int mMovieNameResourceId;
    private final int mMovieDescreptionResourceId;
    private MovieType(@StringRes int movieName,@StringRes int movieDescription){
        mMovieNameResourceId = movieName;
        mMovieDescreptionResourceId = movieDescription;
    }
    @StringRes
    public int getMovieNameResourceId(){
        return mMovieNameResourceId;
    }
    @StringRes
    public int getMovieDescreptionResourceId(){
        return mMovieDescreptionResourceId;
    }
}
