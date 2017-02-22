package com.sharon.w1a_flicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private MovieAdapter mListAdapter;
    private MovieGson mData;

    private String themoviedbApi = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Movielist(themoviedbApi);

    }

    private void setList() {
        mListView = (ListView) findViewById(R.id.lstMovie); //產生ListView的元件資源
        mListAdapter = new MovieAdapter(this, mData);       //建立Adapter的Instance
        mListView.setAdapter(mListAdapter);                 //傳入Adapter的Instance
    }

    private void Movielist(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String successResponse) {
                // 當取得API回傳結果成功時要做的處理
                Gson gson = new Gson();
                mData = gson.fromJson(successResponse, MovieGson.class); //將資料模型存到Global變數mData裡
                setList();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // 當取得API回傳結果失敗時要做的處理
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

        });
    }

}
