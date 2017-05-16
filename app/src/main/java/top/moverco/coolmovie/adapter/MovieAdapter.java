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
 * Created by liuzongxiang on 11/05/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> mMovies;
    Context mContext;
    private MovieItemClickListener mListener;

    public MovieAdapter(Context context, @NonNull List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false), mListener);
        return holder;
    }

    public void setOnItemClickListener(MovieItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MyViewHolder holder, int position) {
        holder.movieTitle.setText(mMovies.get(position).getTitle());
        holder.release_date.setText(mMovies.get(position).getRelease_date());
        holder.original_title.setText(mMovies.get(position).getOriginal_title());
        holder.original_language.setText(mMovies.get(position).getOriginal_language());
        holder.popularity.setText(mMovies.get(position).getPopuarityAsString());
        holder.rate.setText(mMovies.get(position).getVoteAverageAsString());
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
        TextView release_date;
        TextView original_title;
        TextView original_language;
        TextView popularity;
        TextView rate;

        public MyViewHolder(View itemView, MovieItemClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            moviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
            release_date = (TextView) itemView.findViewById(R.id.release_date);
            original_title = (TextView) itemView.findViewById(R.id.original_title);
            original_language = (TextView) itemView.findViewById(R.id.original_language);
            popularity = (TextView) itemView.findViewById(R.id.popularity);
            rate = (TextView) itemView.findViewById(R.id.rate);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
