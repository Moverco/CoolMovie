package top.moverco.coolmovie.common.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import top.moverco.coolmovie.common.database.MovieConstract.MovieEntry;

/**
 * Created by liuzongxiang on 16/05/2017.
 */

public class MovieProvider extends ContentProvider {
    public static final int RATE_MOVIE = 0;
    public static final int POP_MOVIE = 2;

    private MovieOpenHelper mHelper;
    private static final SQLiteQueryBuilder movieSQLiteBuilder;

    private static UriMatcher uriMatcher;

    static {
        movieSQLiteBuilder = new SQLiteQueryBuilder();

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MovieConstract.CONTENT_AUTHORITY, "rate", RATE_MOVIE);
        uriMatcher.addURI(MovieConstract.CONTENT_AUTHORITY, "pop", POP_MOVIE);
    }

    @Override
    public boolean onCreate() {
        mHelper = new MovieOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor retCursor;
        switch (uriMatcher.match(uri)) {
            case RATE_MOVIE:
                retCursor = mHelper.getReadableDatabase().query(
                        MovieEntry.RATE_TABLE,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case POP_MOVIE:
                retCursor = mHelper.getReadableDatabase().query(
                        MovieEntry.POP_TABLE,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case RATE_MOVIE:
                return MovieEntry.CONTENT_TYPE_RATE;
            case POP_MOVIE:
                return MovieEntry.CONTENT_TYPE_POP;
//            case RATE_MOVIE_ITEM:
//                return MovieConstract.MovieEntry.CONTENT_TYPE_ITEM_RATE;
//            case POP_MOVIE_ITEM:
//                return MovieConstract.MovieEntry.CONTENT_TYPE_ITEM_POP;
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        Uri returnUri;
        long _id;
        switch (match) {
            case RATE_MOVIE:
                _id = db.insert(MovieEntry.RATE_TABLE, null, values);
                if (_id > 0) {
                    returnUri = MovieEntry.buildLocationUri(MovieEntry.CONTENT_URI_RATE, _id);
                } else throw new SQLException("Failed to insert row into " + uri);
                break;
            case POP_MOVIE:
                _id = db.insert(MovieEntry.POP_TABLE, null, values);
                if (_id > 0)
                    returnUri = MovieEntry.buildLocationUri(MovieEntry.CONTENT_URI_POP, _id);
                else throw new SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowDeleted;
        if (selection==null)selection = "1";
        switch (match){
            case RATE_MOVIE:
                rowDeleted = db.delete(MovieEntry.RATE_TABLE,selection,selectionArgs);
                break;
            case POP_MOVIE:
                rowDeleted = db.delete(MovieEntry.POP_TABLE,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case RATE_MOVIE:
                rowsUpdated = db.update(MovieEntry.RATE_TABLE, values, selection,
                        selectionArgs);
                break;
            case POP_MOVIE:
                rowsUpdated = db.update(MovieEntry.POP_TABLE, values, selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public void shutdown() {
        mHelper.close();
        super.shutdown();
    }
}
