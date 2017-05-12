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
    public static final String DB_NAME = "coolmovie";
    private static final String CREATE_RATE_TABLE = "create table rate(" +
            "id integer not null primary key," +
            "title text not null," +
            "overview text," +
            "poster_path text," +
            "release_date text," +
            "popularity real," +
            "vote_average real," +
            "vote_count integer," +
            "original_title text," +
            "original_language text," +
            "backdrop_path text)";
    private static final String CREATE_POP_TABLE = "create table pop(" +
            "id integer not null primary key," +
            "title text not null," +
            "overview text," +
            "poster_path text," +
            "release_date text," +
            "popularity real," +
            "vote_average real," +
            "vote_count integer," +
            "original_title text," +
            "original_language text," +
            "backdrop_path text)";
    public MovieOpenHelper(Context context){
        super(context,DB_NAME,null,1);
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
