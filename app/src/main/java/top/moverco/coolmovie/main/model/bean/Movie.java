package top.moverco.coolmovie.main.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import top.moverco.coolmovie.common.util.JsonParseUtil;
import top.moverco.coolmovie.common.util.MovieURLUtil;
import top.moverco.coolmovie.entity.Vedio;

public class Movie implements Parcelable {
    /**
     * poster_path : /ySiqbi1sW7imVYbtECZS0xQ3Hmj.jpg
     * adult : false
     * overview : Long buried secrets finally come to light as someone has been playing a very long game indeed. Sherlock and John face their greatest ever challenge. Is the game finally over?
     * release_date : 2017-01-15
     * genre_ids : [9648,18]
     * id : 432517
     * original_title : Sherlock: The Final Problem
     * original_language : en
     * title : Sherlock: The Final Problem
     * backdrop_path : /3nXbWFPFGvjSE5bw8G6bBLVIzow.jpg
     * popularity : 2.460336
     * vote_count : 85
     * video : false
     * vote_average : 8.6
     */
    private String poster_path;
    private String release_date;
    private int id;
    private String overview;
    private String title;
    private double popularity;
    private double vote_average;
    private int vote_count;
    private String original_title;
    private String original_language;
    private String backdrop_path;
    private int runtime;
    private String[] reviews;
    private Vedio mVedio;

    public int getRuntime() {
        int time_length = 0;
        JsonParseUtil.parse(MovieURLUtil.getRuntimeURL(this.id),null);
        return time_length ;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }
    public String getPopuarityAsString(){
        return String.format("%.2f", popularity);
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVote_average() {
        return vote_average;
    }
    public String getVoteAverageAsString(){
        return String.valueOf(vote_average);
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public String toString() {
        return "Title:"+getTitle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeInt(this.id);
        dest.writeString(this.overview);
        dest.writeString(this.title);
        dest.writeDouble(this.popularity);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.vote_count);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.backdrop_path);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.poster_path = in.readString();
        this.release_date = in.readString();
        this.id = in.readInt();
        this.overview = in.readString();
        this.title = in.readString();
        this.popularity = in.readDouble();
        this.vote_average = in.readDouble();
        this.vote_count = in.readInt();
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.backdrop_path = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}