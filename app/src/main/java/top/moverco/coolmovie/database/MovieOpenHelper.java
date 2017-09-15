package top.moverco.coolmovie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import top.moverco.coolmovie.util.LoggerUtil;

/**
 * Created by liuzongxiang on 12/05/2017.
 */

public class MovieOpenHelper extends SQLiteOpenHelper {

    //    private String poster_path;
//    private String release_date;
//    private int id;
//    private String overview;
//    private String title;
//    private double popularity;
//    private double vote_average;

    //    private int vote_count;
//    private String original_title;
//    private String original_language;
//    private String backdrop_path;
    public static final String DB_NAME = "coolmovie.db";
    public static final int DB_VERSION = 1;
    private static final String CREATE_RATE_TABLE = "create table "+MovieConstract.MovieEntry.RATE_TABLE+"(" +
            MovieConstract.MovieEntry.ID+" integer not null primary key," +
            MovieConstract.MovieEntry.TITLE+" text not null," +
            MovieConstract.MovieEntry.OVERVIEW+" text," +
            MovieConstract.MovieEntry.POSTER_PATH+" text," +
            MovieConstract.MovieEntry.RELEASE_DATE+" text," +
            MovieConstract.MovieEntry.POPULARITY+" real," +
            MovieConstract.MovieEntry.VOTE_AVERAGE+" real," +
            MovieConstract.MovieEntry.VOTE_COUNT+" integer," +
            MovieConstract.MovieEntry.ORIGINAL_TITLE+" text," +
            MovieConstract.MovieEntry.ORIGINAL_LANGUAGE+" text," +
            MovieConstract.MovieEntry.BACKDROP_PATH+" text)";
    private static final String CREATE_POP_TABLE = "create table "+MovieConstract.MovieEntry.POP_TABLE+"(" +
            MovieConstract.MovieEntry.ID+" integer not null primary key," +
            MovieConstract.MovieEntry.TITLE+" text not null," +
            MovieConstract.MovieEntry.OVERVIEW+" text," +
            MovieConstract.MovieEntry.POSTER_PATH+" text," +
            MovieConstract.MovieEntry.RELEASE_DATE+" text," +
            MovieConstract.MovieEntry.POPULARITY+" real," +
            MovieConstract.MovieEntry.VOTE_AVERAGE+" real," +
            MovieConstract.MovieEntry.VOTE_COUNT+" integer," +
            MovieConstract.MovieEntry.ORIGINAL_TITLE+" text," +
            MovieConstract.MovieEntry.ORIGINAL_LANGUAGE+" text," +
            MovieConstract.MovieEntry.BACKDROP_PATH+" text)";
    public MovieOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    public MovieOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POP_TABLE);
        db.execSQL(CREATE_RATE_TABLE);
        LoggerUtil.debug("create table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}