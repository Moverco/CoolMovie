package top.moverco.coolmovie.entity;

public class Movie {
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
}