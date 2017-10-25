package top.moverco.coolmovie.common.util;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.net.Uri;

import top.moverco.coolmovie.common.database.MovieConstract;
import top.moverco.coolmovie.common.database.MovieProvider;
import top.moverco.coolmovie.main.model.bean.Movie;

/**
 * Created by liuzongxiang on 18/05/2017.
 */

public class MovieDBUtil {
    public static final int SAVE_TO_RATE_TABLE = 0x0001;
    public static final int SAVE_TO_POP_TABLE = 0x0002;

    private UriMatcher mUriMatcher;


    MovieProvider mProvider = new MovieProvider();
    public void saveMovieToDatabase(Movie movie,Uri uri){
        switch (mUriMatcher.match(uri)){
            case MovieProvider.RATE_MOVIE:
                ContentValues values = new ContentValues();
                values.put(MovieConstract.MovieEntry.ID,movie.getId());
                values.put(MovieConstract.MovieEntry.TITLE,movie.getTitle());
                values.put(MovieConstract.MovieEntry.BACKDROP_PATH,movie.getBackdrop_path());
                values.put(MovieConstract.MovieEntry.OVERVIEW,movie.getOverview());
                values.put(MovieConstract.MovieEntry.ORIGINAL_LANGUAGE,movie.getOriginal_language());
                values.put(MovieConstract.MovieEntry.ORIGINAL_TITLE,movie.getOriginal_title());
                values.put(MovieConstract.MovieEntry.RELEASE_DATE,movie.getRelease_date());
                values.put(MovieConstract.MovieEntry.POPULARITY,movie.getPopularity());
                values.put(MovieConstract.MovieEntry.VOTE_COUNT,movie.getVote_count());
                values.put(MovieConstract.MovieEntry.VOTE_AVERAGE,movie.getVote_average());
        }
    }
}
