package top.moverco.coolmovie.util;


/**
 * Created by liuzongxiang on 11/05/2017.
 */

public class MovieURLUtil {
    public static final String HTTP_ROOT = "https://api.themoviedb.org/3/movie/%s?api_key=%s";
    public static final String API_KEY_V3 = "f98d0ce3f94249a434d6d7faf25cf20a";
    public static final String POSTER_ROOT = "https://image.tmdb.org/t/p/w1280/%s";

    public static final int UPCOMING = 0x0001;
    public static final int LATEST = 0x0010;
    public static final int POPULAR = 0x0100;
    public static final int TOP_RATED = 0x1000;

    private final static String _UPCOMING = "upcoming";
    private final static String _LATEST = "latest";
    private final static String _POPULAR = "popular";
    private final static String _TOP_RATED = "top_rated";

    private static final String GET_UPCOMING_URL = String.format(HTTP_ROOT, _UPCOMING, API_KEY_V3);
    private static final String GET_LATEST_URL = String.format(HTTP_ROOT, _LATEST, API_KEY_V3);
    public static final String GET_POPULAR_URL = String.format(HTTP_ROOT, _POPULAR, API_KEY_V3);
    public static final String GET_TOP_RATED_ROOT_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=f98d0ce3f94249a434d6d7faf25cf20a";


    public static String getURL(int msg) {
        switch (msg) {
            case UPCOMING:
                return GET_UPCOMING_URL;
            case LATEST:
                return GET_LATEST_URL;
            case POPULAR:
                return GET_POPULAR_URL;
            case TOP_RATED:
                return GET_TOP_RATED_ROOT_URL;
            default:break;
        }
        return null;
    }

    public static String getPosterURL(String string){
        return  String.format(POSTER_ROOT,string);
    }

}
