package com.sharon.w1a_flicks;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.sharon.w1a_flicks.R.id.img_backdrop;

/**
 * Created by sharonwang on 2017/2/16.
 */

public class DetailActivity extends AppCompatActivity {

    private TextView txtOverview;
    private TextView txtReleaseDate;
    private TextView txtTitle;
    private ImageView imgbackdrop;
    private RatingBar rating;
    private String mTextOverview;
    private String mReleaseDate;
    private String mVideoId;
    private String mTitle;
    private String mImagePath;
    private float mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViews();
        getdata();
        setData();
        setListener();
    }

    private void findViews() {
        setContentView(R.layout.activity_detail);
        txtOverview = (TextView) findViewById(R.id.txt_overview);
        txtReleaseDate = (TextView) findViewById(R.id.txt_release_date);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        imgbackdrop = (ImageView) findViewById(R.id.img_backdrop);
        rating = (RatingBar) findViewById(R.id.rating);

    }

    private void getdata() {
        Bundle bundle = getIntent().getExtras();
        mTextOverview = bundle.getString("overview");
        mReleaseDate = bundle.getString("release_date");
        mVideoId = bundle.getString("videoId");
        mTitle = bundle.getString("title");
        mImagePath = bundle.getString("image");
        mRating = bundle.getFloat("rating");
    }

    private void setData() {

        txtOverview.setText(mTextOverview);
        txtReleaseDate.setText(mReleaseDate);
        txtTitle.setText(mTitle);
        rating.setRating(mRating);

        Picasso.with(this).load("https://image.tmdb.org/t/p/w780" + mImagePath).into(imgbackdrop);
    }

    private void setListener() {

    }
}
