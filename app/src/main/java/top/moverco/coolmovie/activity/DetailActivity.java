package top.moverco.coolmovie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.moverco.coolmovie.R;
import top.moverco.coolmovie.util.ImageLoader;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;

/**
 * Created by liuzongxiang on 12/05/2017.
 */

public class DetailActivity extends AppCompatActivity {


    @BindView(R.id.detail_backdrop) ImageView detail_backdrop;
    @BindView(R.id.detail_title) TextView detail_title;
    @BindView(R.id.detail_overview) TextView detail_overview;
    @BindView(R.id.detail_release_date) TextView detail_release_date;
    @BindView(R.id.detail_rate) TextView detail_rate;


    Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        LoggerUtil.debug("detail activity");
        super.onCreate(savedInstanceState, persistentState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detail_layout);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        detail_title.setText(intent.getStringExtra("title"));
        detail_overview.setText(intent.getStringExtra("overview"));
        detail_release_date.setText(intent.getStringExtra("release_date"));
        detail_rate.setText(intent.getStringExtra("rate"));
        String backdrop_path = intent.getStringExtra("backdrop_path");
        ImageLoader loader = ImageLoader.build(DetailActivity.this);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int display_width = metrics.widthPixels;
        loader.setReqheight(display_width).setReqWidth(display_width);
        loader.setReqheight(320).setReqWidth(320);
        loader.bindBitmap(MovieURLUtil.getPosterURL(backdrop_path),detail_backdrop);
    }

}
