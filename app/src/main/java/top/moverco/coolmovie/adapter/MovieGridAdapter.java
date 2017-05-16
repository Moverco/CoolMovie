package top.moverco.coolmovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import top.moverco.coolmovie.R;
import top.moverco.coolmovie.entity.Movie;
import top.moverco.coolmovie.util.MovieURLUtil;

/**
 * Created by liuzongxiang on 16/05/2017.
 */

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MyViewHolder> {
    private List<Movie> mMovies;
    Context mContext;
    private MovieItemClickListener mListener;

    public MovieGridAdapter(Context context, @NonNull List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
    }

    @Override
    public MovieGridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.grid_item_layout, parent, false), mListener);
        return holder;
    }

    public void setOnItemClickListener(MovieItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(MovieGridAdapter.MyViewHolder holder, int position) {
        holder.movieTitle.setText(mMovies.get(position).getTitle());
        loadBitmap(mMovies.get(position), holder.moviePoster);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    private void loadBitmap(Movie movie, ImageView imageView) {
        String posterPath = MovieURLUtil.getPosterURL(movie.getPoster_path());
        Glide.with(mContext).load(posterPath).centerCrop().placeholder(R.mipmap.movie_load128).crossFade().into(imageView);

    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MovieItemClickListener mListener;

        TextView movieTitle;
        ImageView moviePoster;


        public MyViewHolder(View itemView, MovieItemClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            movieTitle = (TextView) itemView.findViewById(R.id.grid_title);
            moviePoster = (ImageView) itemView.findViewById(R.id.grid_poster);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
