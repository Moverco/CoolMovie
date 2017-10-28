package top.moverco.coolmovie.account;

import java.util.List;

/**
 * Created by Moverco.
 */

public class CreatedLists {


    /**
     * page : 1
     * results : [{"description":"Name pretty much says it all, here's the top 50 grossing films of all time.","favorite_count":0,"id":10,"item_count":0,"iso_639_1":"en","list_type":"movie","name":"Top 50 Grossing Films of All Time (Worldwide)","poster_path":null}]
     * total_pages : 4
     * total_results : 61
     */

    private int page;
    private int total_pages;
    private int total_results;
    private List<ResultsBean> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * description : Name pretty much says it all, here's the top 50 grossing films of all time.
         * favorite_count : 0
         * id : 10
         * item_count : 0
         * iso_639_1 : en
         * list_type : movie
         * name : Top 50 Grossing Films of All Time (Worldwide)
         * poster_path : null
         */

        private String description;
        private int favorite_count;
        private int id;
        private int item_count;
        private String iso_639_1;
        private String list_type;
        private String name;
        private Object poster_path;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getItem_count() {
            return item_count;
        }

        public void setItem_count(int item_count) {
            this.item_count = item_count;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getList_type() {
            return list_type;
        }

        public void setList_type(String list_type) {
            this.list_type = list_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(Object poster_path) {
            this.poster_path = poster_path;
        }
    }
}
