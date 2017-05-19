package top.moverco.coolmovie.util;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import top.moverco.coolmovie.entity.Movie;

/**
 * Created by liuzongxiang on 11/05/2017.
 */

public class JsonParseUtil {

    public static List<Movie> parse(String jsonString) {
        List<Movie> movies = new ArrayList<>();
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray("results");

        Gson gson = new Gson();

        //循环遍历
        for (JsonElement mov : jsonArray) {
            //通过反射 得到UserBean.class
            Movie movie = gson.fromJson(mov, new TypeToken<Movie>() {}.getType());
            movies.add(movie);
            LoggerUtil.debug(movie.toString());
        }
        return movies;
    }

    // TODO: 18/05/2017  
    public static Movie parse(String jsonString,@Nullable String tag) {
        Movie movie = new Movie();
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();

        Gson gson = new Gson();
        return movie;
    }



}
