package top.moverco.coolmovie.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import top.moverco.coolmovie.R;
import top.moverco.coolmovie.activity.DetailActivity;
import top.moverco.coolmovie.adapter.MovieAdapter;
import top.moverco.coolmovie.adapter.MovieItemClickListener;
import top.moverco.coolmovie.entity.Movie;
import top.moverco.coolmovie.util.JsonParseUtil;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;



public class PopularSortedFragment extends Fragment {
    List<Movie> mMovies = new ArrayList<>();
    Context mContext;
    RecyclerView mRecyclerView;
    public static boolean mIsRecyclerviewIdle = false;
    MovieAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popular_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_popular_fragment);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                   mIsRecyclerviewIdle = true;
               }else mIsRecyclerviewIdle = false;
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoggerUtil.debug("pop get movie");
        loadMovies();
    }



    private void loadMovies() {
        OkHttpUtils.get()
                .url(MovieURLUtil.GET_POPULAR_URL)
                .build()
                .writeTimeOut(8000)
                .connTimeOut(8000)
                .readTimeOut(8000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, "Network state is abnormal", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoggerUtil.debug(response);
                        mMovies.clear();
                        mMovies = JsonParseUtil.parse(response);
                        mAdapter = new MovieAdapter(mContext, mMovies);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MovieItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(mContext,mMovies.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putString("title",mMovies.get(position).getTitle());
                                bundle.putString("overview",mMovies.get(position).getOverview());
                                bundle.putString("poster_path",mMovies.get(position).getPoster_path());
                                bundle.putString("backdrop_path",mMovies.get(position).getBackdrop_path());
                                bundle.putString("release_date",mMovies.get(position).getRelease_date());
                                bundle.putString("rate",mMovies.get(position).getVoteAverageAsString());
                                Intent intent = new Intent(getActivity(), DetailActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    public void refreshMovies() {
        loadMovies();
    }
}
