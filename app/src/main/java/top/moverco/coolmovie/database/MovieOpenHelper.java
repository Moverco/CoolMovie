package top.moverco.coolmovie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    private static final String CREATE_RATE_TABLE = "create table rate if not exist(" +
            "id integer not null," +
            "title text not null," +
            "overview text," +
            "poster_path text," +
            "release_data text," +
            "popularity real," +
            "vote_average real" +
            "vote_count integer" +
            "original_title text" +
            "original_language text" +
            "backdrop_paht text)";
    private static final String CREATE_POP_TABLE = "create table pop if not exist(" +
            "id integer not null," +
            "title text not null," +
            "overview text," +
            "poster_path text," +
            "release_data text," +
            "popularity real," +
            "vote_average real" +
            "vote_count integer" +
            "original_title text" +
            "original_language text" +
            "backdrop_paht text)";
    public MovieOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POP_TABLE);
        db.execSQL(CREATE_RATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
