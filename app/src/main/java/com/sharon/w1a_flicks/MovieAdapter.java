package com.sharon.w1a_flicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by sharonwang on 2017/2/16.
 */

public class MovieAdapter extends BaseAdapter {
    private Context mContext;
    private MovieGson mData;


    public MovieAdapter(Context context, MovieGson data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {

        if (mData != null) { //如果有取得api回傳後的資料
            return mData.results.size();
        } else {
            return 0; //設為0則ListView沒有產生item
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewholder;
        int type = getItemViewType(position);

    /* 使用ViewHolder */
        if (view == null) {

            //產生不同的item layout
            if(type == 0){
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_type1, parent, false);
            }else{
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_type2, parent, false);
            }

            viewholder = new ViewHolder();
            viewholder.imagePoster = (ImageView) view.findViewById(R.id.image_poster);
            viewholder.textTitle = (TextView) view.findViewById(R.id.text_title);
            viewholder.textOverview = (TextView) view.findViewById(R.id.text_overview);

            view.setTag(viewholder);

        } else {
            viewholder = (ViewHolder) view.getTag();
        }

    /* 設定不同的item layout */
        if (type == 0) {

            //設定item UI元件的顯示資料
            viewholder.textTitle.setText(mData.results.get(position).title);
            viewholder.textOverview.setText(mData.results.get(position).overview);

            Picasso.with(mContext).load(getImageUrl(mData.results.get(position).poster_path))
                    .into(viewholder.imagePoster);

            //設定item的click listener
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoDetailActivity(position); //前往某個Activity(自行修改成要觸發的行為)
                }
            });
        } else {

            //設定item UI元件的顯示資料
            Picasso.with(mContext).load(getImageUrl(mData.results.get(position).backdrop_path))
                    .into(viewholder.imagePoster);

            //設定item的click listener
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    gotoYoutubeActivity(position); //前往某個Activity(自行修改成要觸發的行為)
                }
            });
        }

        return view;
    }

    private class ViewHolder {
        ImageView imagePoster;
        TextView textTitle;
        TextView textOverview;
    }

    private String getImageUrl(String urlFromApi) {
        String prefix = "https://image.tmdb.org/t/p/w780";
        String urlArray = urlFromApi.substring(0, urlFromApi.length());
        return prefix + urlArray;
    }

    @Override
    public int getItemViewType(int position) {

        if (mData.results.get(position).vote_average >= 5.0f) {
            return 0; //產生類型0的layout
        } else {
            return 1; //產生類型1的layout
        }
    }

    @Override
    public int getViewTypeCount() {

        return 2; //這個ListView有兩種類型的item layout
    }


    private void gotoDetailActivity(int position) {
        if (mData != null) {
            Intent intent = new Intent();
            intent.setClass(mContext, DetailActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("overview", mData.results.get(position).overview);
            bundle.putString("release_date", mData.results.get(position).release_date);
            bundle.putString("videoId", mData.results.get(position).id);
            bundle.putString("title", mData.results.get(position).title);
            bundle.putString("image", mData.results.get(position).backdrop_path);
            bundle.putFloat("rating", mData.results.get(position).vote_average);

            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
    }
}
