package top.moverco.coolmovie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import top.moverco.coolmovie.entity.Movie;
import top.moverco.coolmovie.util.LoggerUtil;

import static android.os.Build.ID;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.BACKDROP_PATH;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.ORIGINAL_LANGUAGE;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.ORIGINAL_TITLE;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.OVERVIEW;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.POPULARITY;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.POP_TABLE;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.POSTER_PATH;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.RATE_TABLE;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.RELEASE_DATE;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.VOTE_AVERAGE;
import static top.moverco.coolmovie.database.MovieConstract.MovieEntry.VOTE_COUNT;

/**
 * Created by liuzongxiang on 12/05/2017.
 */

public class MovieDB {
    private static MovieDB mMovieDB;
    private SQLiteDatabase db;

    //            "id integer not null," +
//            "title text not null," +
//            "overview text," +
//            "poster_path text," +
//            "release_date text," +
//            "popularity real," +
//            "vote_average real" +
//            "vote_count integer" +
//            "original_title text" +
//            "original_language text" +
//            "backdrop_path text";
//


    private static final String DELETE_FROM_TABLE = "delete from ";

    private static String QUERY_RATE = "select * from "+ MovieConstract.MovieEntry.RATE_TABLE+" order by "+VOTE_AVERAGE+" desc";
    private static String QUERY_POP = "select * from "+ MovieConstract.MovieEntry.POP_TABLE+" order by "+VOTE_AVERAGE+" desc";


    private MovieDB(Context context) {
        MovieOpenHelper helper = new MovieOpenHelper(context);
        db = helper.getWritableDatabase();
        LoggerUtil.debug("init moviedb");
    }

    public static MovieDB getInstance(Context context) {
        if (mMovieDB == null) {
            synchronized (MovieDB.class) {
                if (mMovieDB == null) {
                    mMovieDB = new MovieDB(context);
                }
            }
        }
        return mMovieDB;
    }



    private void saveMovieToDatabase(Movie movie, String tableName) {
        if (!tableName.equals(RATE_TABLE) && !tableName.equals(POP_TABLE))
            return;
        if (movie != null) {
            ContentValues values = new ContentValues();
            values.put(MovieConstract.MovieEntry.ID, movie.getId());
            values.put(MovieConstract.MovieEntry.TITLE, movie.getTitle());
            values.put(OVERVIEW, movie.getOverview());
            values.put(POSTER_PATH, movie.getPoster_path());
            values.put(RELEASE_DATE, movie.getRelease_date());
            values.put(POPULARITY, movie.getPopularity());
            values.put(VOTE_AVERAGE, movie.getVote_average());
            values.put(VOTE_COUNT, movie.getVote_count());
            values.put(ORIGINAL_TITLE, movie.getOriginal_title());
            values.put(ORIGINAL_LANGUAGE, movie.getOriginal_language());
            values.put(BACKDROP_PATH, movie.getBackdrop_path());
            db.insert(tableName, null, values);
        }
    }
    private void saveMovieListToDatabase(List<Movie> movies,String tableName){
        if (!tableName.equals(RATE_TABLE) && !tableName.equals(POP_TABLE))
            return;
        Cursor cursor = db.query(tableName,null,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            db.execSQL(DELETE_FROM_TABLE+tableName);
            LoggerUtil.debug("Clear table "+tableName);
        }
        if (movies!=null){
            for (Movie movie:movies){
                saveMovieToDatabase(movie,tableName);
                LoggerUtil.debug("saved:"+ movie.getTitle());
            }
        }
    }

    public void saveMovieToRateTable(Movie movie) {
        saveMovieToDatabase(movie, RATE_TABLE);
    }

    public void saveMovieToPopTable(Movie movie) {
        saveMovieToDatabase(movie, POP_TABLE);
    }

    public void saveMovieListToRateTable(List<Movie> movies) {
        saveMovieListToDatabase(movies,RATE_TABLE);
    }
    public void saveMovieListToPopTable(List<Movie> movies){
        saveMovieListToDatabase(movies,POP_TABLE);
    }

    private List<Movie> getMovieListFromDatabase(String tableName){
        LoggerUtil.debug("get movies from database");
        if (!tableName.equals(RATE_TABLE) && !tableName.equals(POP_TABLE))
            return null;
        List<Movie> movies = new ArrayList<>();
        String order_string;
        if (tableName.equals(RATE_TABLE)){
            order_string = VOTE_AVERAGE;
        }else order_string = POPULARITY;
        String query =  "select * from "+tableName+" order by "+order_string+" desc";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieConstract.MovieEntry.TITLE)));
                movie.setBackdrop_path(cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
                movie.setOriginal_language(cursor.getString(cursor.getColumnIndex(ORIGINAL_LANGUAGE)));
                movie.setOriginal_title(cursor.getString(cursor.getColumnIndex(ORIGINAL_TITLE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
                movie.setPopularity(cursor.getDouble(cursor.getColumnIndex(POPULARITY)));
                movie.setPoster_path(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
                movie.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
                movie.setVote_average(cursor.getDouble(cursor.getColumnIndex(VOTE_AVERAGE)));
                movie.setVote_count(cursor.getInt(cursor.getColumnIndex(VOTE_COUNT)));
                movies.add(movie);
            }while (cursor.moveToNext());
        }
        LoggerUtil.debug(movies.toString());
        return movies;
    }
    public List<Movie> getMovieListFromPopTable(){
        return getMovieListFromDatabase(POP_TABLE);
    }
    public List<Movie> getMovieListFromRatetable(){
        return getMovieListFromDatabase(RATE_TABLE);
    }
    public void printDatabase(String tableName){
        if (!tableName.equals(RATE_TABLE) && !tableName.equals(POP_TABLE))
            return;
        List<Movie> movies = getMovieListFromDatabase(tableName);
        LoggerUtil.debug(movies.toString());
    }
}