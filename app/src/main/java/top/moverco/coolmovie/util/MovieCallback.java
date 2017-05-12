package top.moverco.coolmovie.util;


import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;
import top.moverco.coolmovie.entity.Movie;

/**
 * Created by liuzongxiang on 11/05/2017.
 */

public abstract class MovieCallback extends Callback<List<Movie>> {
    @Override
    public List<Movie> parseNetworkResponse(Response response, int id) throws Exception {
        String jsonString = response.body().toString();
        LoggerUtil.debug(jsonString);
        LoggerUtil.debug(response.toString());
        List<Movie> movies = JsonParseUtil.parse(jsonString);
        return movies;
    }
}
