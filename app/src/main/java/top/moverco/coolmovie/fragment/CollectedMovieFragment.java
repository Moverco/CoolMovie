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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.moverco.coolmovie.R;
import top.moverco.coolmovie.activity.DetailActivity;
import top.moverco.coolmovie.adapter.MovieAdapter;
import top.moverco.coolmovie.adapter.MovieItemClickListener;
import top.moverco.coolmovie.entity.Movie;
import top.moverco.coolmovie.util.LoggerUtil;
import top.moverco.coolmovie.util.MovieURLUtil;

/**
 * Created by liuzongxiang on 18/05/2017.
 */

public class CollectedMovieFragment extends Fragment {
        private RecyclerView mRecyclerView;
        Context mContext;
        List<Movie> mMovies = new ArrayList<>();
        public static boolean mIsRecyclerviewIdle = false;
        MovieAdapter mAdapter;
        ProgressBar mProgressBar;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mContext = context;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.list_fragment, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                        mIsRecyclerviewIdle = true;
                    } else mIsRecyclerviewIdle = false;
                }
            });
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            return view;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            LoggerUtil.debug("rate get movie");
            LoggerUtil.debug("url:" + MovieURLUtil.GET_TOP_RATED_ROOT_URL);
//        loadMovies();
            if (mMovies.isEmpty()) {
                initMovies();
            }

        }

    void initMovies() {
        for (int i = 0; i < 20; i++) {
            Movie movie = new Movie();
            movie.setTitle("Cmovie"+i);
            mMovies.add(movie);
        }
        MovieAdapter adapter = new MovieAdapter(mContext,mMovies);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MovieItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, mMovies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("movie", mMovies.get(position));
                startActivity(intent);
            }
        });
    }


}
