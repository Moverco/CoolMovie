package top.moverco.coolmovie.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.moverco.coolmovie.R;
import top.moverco.coolmovie.main.model.bean.Movie;
import top.moverco.coolmovie.common.util.LoggerUtil;
import top.moverco.coolmovie.common.util.MovieUtil;
import top.moverco.coolmovie.view.CaptionedImageView;


public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    @BindView(R.id.detail_backdrop)
    CaptionedImageView detail_backdrop;
    @BindView(R.id.detail_overview)
    TextView detail_overview;
    @BindView(R.id.detail_release_date)
    TextView detail_release_date;
    @BindView(R.id.detail_rate)
    TextView detail_rate;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    Context mContext = this;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        LoggerUtil.debug(movie.toString());
        mToolbar.setTitle(movie.getTitle());
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        detail_overview.setText(movie.getOverview());
        detail_release_date.setText(movie.getRelease_date());
        detail_rate.setText(movie.getVoteAverageAsString());
        detail_backdrop.getTextView().setText(movie.getTitle());
        loadBitmap(movie, detail_backdrop.getImageView());
        detail_backdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showPoster = new Intent(DetailActivity.this, PosterActivity.class);
                startActivity(showPoster);
            }
        });
    }

    private void loadBitmap(Movie movie, ImageView imageView) {
        MovieUtil movieUtil = new MovieUtil(mContext);
        movieUtil.bindMovieBackdrop(movie, imageView);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
