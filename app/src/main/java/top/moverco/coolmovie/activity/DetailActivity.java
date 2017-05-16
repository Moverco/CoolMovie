package top.moverco.coolmovie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.moverco.coolmovie.R;
import top.moverco.coolmovie.entity.Movie;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;


public class DetailActivity extends AppCompatActivity {


    @BindView(R.id.detail_backdrop) ImageView detail_backdrop;
    @BindView(R.id.detail_title) TextView detail_title;
    @BindView(R.id.detail_overview) TextView detail_overview;
    @BindView(R.id.detail_release_date) TextView detail_release_date;
    @BindView(R.id.detail_rate) TextView detail_rate;


    Context mContext = this;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        LoggerUtil.debug(movie.toString());
        detail_title.setText(movie.getTitle());
        detail_overview.setText(movie.getOverview());
        detail_release_date.setText(movie.getRelease_date());
        detail_rate.setText(movie.getVoteAverageAsString());
        loadBitmap(movie,detail_backdrop);
    }
    private void loadBitmap(Movie movie,ImageView imageView){
        String posterPath = MovieURLUtil.getPosterURL(movie.getPoster_path());
        Glide.with(mContext).load(posterPath).centerCrop().placeholder(R.mipmap.loading).crossFade().into(imageView);
    }
}
