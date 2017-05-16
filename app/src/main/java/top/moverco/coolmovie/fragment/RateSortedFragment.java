package top.moverco.coolmovie.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import top.moverco.coolmovie.R;
import top.moverco.coolmovie.activity.DetailActivity;
import top.moverco.coolmovie.adapter.MovieGridAdapter;
import top.moverco.coolmovie.adapter.MovieItemClickListener;
import top.moverco.coolmovie.entity.Movie;
import top.moverco.coolmovie.util.JsonParseUtil;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;


public class RateSortedFragment extends Fragment {
    private RecyclerView mRecyclerView;
    Context mContext;
    List<Movie> mMovies = new ArrayList<>();
    public static boolean mIsRecyclerviewIdle = false;
    MovieGridAdapter mAdapter;
    ProgressBar mProgressBar;

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
        mProgressBar = (ProgressBar) view.findViewById(R.id.rate_progress);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    mIsRecyclerviewIdle = true;
                }else mIsRecyclerviewIdle = false;
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoggerUtil.debug("rate get movie");
        LoggerUtil.debug("url:" + MovieURLUtil.GET_TOP_RATED_ROOT_URL);
        loadMovies();

    }

    public void refreshMovies(){
        loadMovies();
    }

    private void loadMovies(){
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
                        mAdapter = new MovieGridAdapter(mContext, mMovies);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MovieItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(mContext,mMovies.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), DetailActivity.class);
                                intent.putExtra("movie",mMovies.get(position));
                                startActivity(intent);
                            }
                        });
                    }
                });

    }
}