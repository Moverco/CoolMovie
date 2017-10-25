package top.moverco.coolmovie.common.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


public class MovieConstract {
    public static final String CONTENT_AUTHORITY = "top.moverco.coolmovie";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class MovieEntry implements BaseColumns {
        public static final String RATE_TABLE = "rate";
        public static final String POP_TABLE = "pop";

        public static final Uri CONTENT_URI_RATE = BASE_CONTENT_URI.buildUpon().appendPath(RATE_TABLE).build();
        public static final Uri CONTENT_URI_POP = BASE_CONTENT_URI.buildUpon().appendPath(POP_TABLE).build();

        public static final String CONTENT_TYPE_RATE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + RATE_TABLE;
        public static final String CONTENT_TYPE_POP = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + POP_TABLE;
        public static final String CONTENT_TYPE_ITEM_RATE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + RATE_TABLE;
        public static final String CONTENT_TYPE_ITEM_POP = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + POP_TABLE;

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";
        public static final String POSTER_PATH = "poster_path";
        public static final String RELEASE_DATE = "release_date";
        public static final String POPULARITY = "popularity";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String VOTE_COUNT = "vote_count";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String ORIGINAL_LANGUAGE = "original_language";
        public static final String BACKDROP_PATH = "backdrop_path";

        public static Uri buildLocationUri(Uri uri, long id) {
            if (uri != CONTENT_URI_RATE && uri != CONTENT_URI_POP) {
                throw new IllegalArgumentException("Illegal param [Uri]:" + uri);
            }
            if (uri == CONTENT_URI_RATE)
                return ContentUris.withAppendedId(CONTENT_URI_RATE, id);
            else return ContentUris.withAppendedId(CONTENT_URI_POP, id);
        }
    }
}
