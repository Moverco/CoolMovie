package top.moverco.coolmovie.util;

import top.moverco.coolmovie.Config.Config;
import top.moverco.coolmovie.entity.Movie;

/**
 * Created by liuzongxiang on 11/05/2017.
 */

public class MovieURLUtil {
    public static final int UPCOMING = 0x0001;
    public static final int LATEST = 0x0010;
    public static final int POPULAR = 0x0100;
    public static final int TOP_RATED = 0x1000;

//    public static String GET_RATE_URL = MovieURLUtil.getURL(MovieURLUtil.TOP_RATED);
//    public static String GET_POP_URL = MovieURLUtil.getURL(MovieURLUtil.POPULAR);

    private final static String _UPCOMING = "upcoming";
    private final static String _LATEST = "latest";
    private final static String _POPULAR = "popular";
    private final static String _TOP_RATED = "top_rated";

    private static final String GET_UPCOMING_URL = String.format(Config.HTTP_ROOT, _UPCOMING, Config.API_KEY_V3);
    private static final String GET_LATEST_URL = String.format(Config.HTTP_ROOT, _LATEST, Config.API_KEY_V3);
    public static final String GET_POPULAR_URL = String.format(Config.HTTP_ROOT, _POPULAR, Config.API_KEY_V3);
    public static final String GET_TOP_RATED_ROOT_URL = String.format(Config.HTTP_ROOT, _TOP_RATED, Config.API_KEY_V3);


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

    public static String getPosterURL(Movie movie){
        return  String.format(Config.POSTER_ROOT,movie.getPoster_path());
    }

}
