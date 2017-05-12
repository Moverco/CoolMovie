package top.moverco.coolmovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import top.moverco.coolmovie.R;
import top.moverco.coolmovie.util.ImageLoader;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;

/**
 * Created by liuzongxiang on 12/05/2017.
 */

public class DetailActivity extends AppCompatActivity {

//    ImageView detail_poster;
    ImageView detail_backdrop;
    TextView detail_title;
    TextView detail_overview;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        initViews();
        Intent intent = getIntent();
        detail_title.setText(intent.getStringExtra("title"));
        detail_overview.setText(intent.getStringExtra("overview"));
        String poster_path = intent.getStringExtra("poster_path");
        String backdrop_path = intent.getStringExtra("backdrop_path");
        LoggerUtil.debug(poster_path);
        ImageLoader loader = ImageLoader.build(DetailActivity.this);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int display_width = metrics.widthPixels;
        loader.setReqheight(display_width).setReqWidth(display_width);
//        loader.bindBitmap(MovieURLUtil.getPosterURL(poster_path),detail_poster);
        loader.bindBitmap(MovieURLUtil.getPosterURL(backdrop_path),detail_backdrop);
    }

    void initViews(){
        detail_backdrop = (ImageView) findViewById(R.id.detail_backdrop);
        detail_overview = (TextView) findViewById(R.id.detail_overview);
//        detail_poster = (ImageView) findViewById(R.id.detail_poster);
        detail_title = (TextView) findViewById(R.id.detail_title);
    }
}
