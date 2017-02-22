package com.sharon.w1a_flicks;

import java.util.ArrayList;

/**
 * Created by sharonwang on 2017/2/19.
 */

public class MovieGson {
    public int page;
    public ArrayList<Results> results = new ArrayList<>();

    public class Results {
        public String poster_path;
        public String overview;
        public String release_date;
        public String id;
        public String title;
        public String backdrop_path;
        public float vote_average;
    }
}
