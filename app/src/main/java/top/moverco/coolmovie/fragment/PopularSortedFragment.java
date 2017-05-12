package top.moverco.coolmovie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import top.moverco.coolmovie.entity.Movie;
import top.moverco.coolmovie.util.JsonParseUtil;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;

/**
 * Created by liuzongxiang on 11/05/2017.
 */

public class PopularSortedFragment extends Fragment implements BaseFragment {
    List<Movie> mMovies = new ArrayList<>();
    Context mContext;
    RecyclerView mRecyclerView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popular_fragment,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_popular_fragment);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoggerUtil.debug("pop get movie");
        getMovies(MovieURLUtil.GET_POPULAR_URL);
    }

    public void getMovies(String url){
        OkHttpUtils.get()
                .url(url)
                .build()
                .writeTimeOut(8000)
                .connTimeOut(8000)
                .readTimeOut(8000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext,"Network state is abnormal",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoggerUtil.debug(response);
                        mMovies.clear();
                        mMovies = JsonParseUtil.parse(response);
                        MovieAdapter adapter = new MovieAdapter(mContext,mMovies);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
    }
}
