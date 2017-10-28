package top.moverco.coolmovie.common.util;


/**
 * Created by liuzongxiang on 11/05/2017.
 */

public class MovieURLUtil {
    //https://api.themoviedb.org/3/movie/76341?api_key=f98d0ce3f94249a434d6d7faf25cf20a&language=zh
    public static final String HTTP_ROOT = "https://api.themoviedb.org/3/movie/%s?api_key=%s";
    public static final String API_KEY_V3 = "f98d0ce3f94249a434d6d7faf25cf20a";
    public static final String POSTER_ROOT = "https://image.tmdb.org/t/p/w1280/%s";
    public static final String RUNTIME_ROOT = "http://api.themoviedb.org/3/movie/%s?api_key=f98d0ce3f94249a434d6d7faf25cf20a";
    public static final String VEDIO_ROOT = "http://api.themoviedb.org/3/movie/%s/videos?api_key=f98d0ce3f94249a434d6d7faf25cf20a";
    public static final String REVIEWS_ROOT = "http://api.themoviedb.org/3/movie/%s/reviews?api_key=f98d0ce3f94249a434d6d7faf25cf20a";

    public static final int UPCOMING = 1;
    public static final int LATEST = 2;
    public static final int POPULAR = 3;
    public static final int TOP_RATED = 4;

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

    public static String getRuntimeURL(int movieId) {
        return String.format(RUNTIME_ROOT,String.valueOf(movieId));
    }
    public static String getVedioURL(int movieId){
        return String.format(VEDIO_ROOT,String.valueOf(movieId));
    }
    public static String getReviewsURL(int movieId){
        return String.format(REVIEWS_ROOT,String.valueOf(movieId));
    }
}
