package top.moverco.coolmovie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import top.moverco.coolmovie.R;
import top.moverco.coolmovie.adapter.MovieAdapter;
import top.moverco.coolmovie.database.MovieDB;
import top.moverco.coolmovie.entity.Movie;
import top.moverco.coolmovie.util.JsonParseUtil;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;

/**
 * Created by liuzongxiang on 11/05/2017.
 */

public class RateSortedFragment extends Fragment {
    private RecyclerView mRecyclerView;
    Context mContext;
    List<Movie> mMovies = new ArrayList<>();
    private MovieDB db;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rate_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_rate_fragment);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoggerUtil.debug("rate get movie");
        LoggerUtil.debug("url" + MovieURLUtil.GET_TOP_RATED_ROOT_URL);
        loadMovies();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                getMovies();
                return true;
        }
        return false;
    }

    private void saveMovies(List<Movie> movies){
        db = MovieDB.getInstance(mContext);
        db.saveMovieListToRateTable(movies);
    }


    public List<Movie> getMoviesFromDatabase(){
        db = MovieDB.getInstance(mContext);
        return db.getMovieListFromRatetable();
    }
    public void refreshMovies(){
        getMovies();
    }
    private void getMovies() {
        OkHttpUtils.get()
                .url(MovieURLUtil.GET_TOP_RATED_ROOT_URL)
                .build()
                .writeTimeOut(8000)
                .connTimeOut(8000)
                .readTimeOut(8000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoggerUtil.debug("rate get movie error");
                        Toast.makeText(mContext,"Network state is abnormal",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mMovies.clear();
                        mMovies = JsonParseUtil.parse(response);
                        MovieAdapter adapter = new MovieAdapter(mContext, mMovies);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
        saveMovies(mMovies);
    }
    private void loadMovies(){
        db = MovieDB.getInstance(mContext);
        if (getMoviesFromDatabase().size()>1){
            LoggerUtil.debug("Load movies from database");
            mMovies = getMoviesFromDatabase();
            LoggerUtil.debug("error road");
            MovieAdapter adapter = new MovieAdapter(mContext,mMovies);
            mRecyclerView.setAdapter(adapter);
        }else {
            getMovies();
        }
    }
}