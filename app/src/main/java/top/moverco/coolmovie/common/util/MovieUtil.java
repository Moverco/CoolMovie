package top.moverco.coolmovie.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import top.moverco.coolmovie.R;
import top.moverco.coolmovie.main.model.bean.Movie;


public class MovieUtil {
    Context mContext;
    int DEFAULT_POSTER_IMG = R.mipmap.movie_load128;

    public MovieUtil(Context context) {
        mContext = context;
    }

    public MovieUtil setContext(Context context) {
        mContext = context;
        return this;
    }

    public  MovieUtil setDefaultPosterImg(int defaultPosterImg) {
        DEFAULT_POSTER_IMG = defaultPosterImg;
        return this;
    }

    public void bindMoviePoster(Movie movie, ImageView imageView){
        String posterPath = MovieURLUtil.getPosterURL(movie.getPoster_path());
        Glide.with(mContext).load(posterPath).centerCrop().placeholder(DEFAULT_POSTER_IMG).crossFade().into(imageView);
    }
    public void bindMovieBackdrop(Movie movie, ImageView imageView){
        String posterPath = MovieURLUtil.getPosterURL(movie.getBackdrop_path());
        Glide.with(mContext).load(posterPath).centerCrop().placeholder(DEFAULT_POSTER_IMG).crossFade().into(imageView);
    }
}
