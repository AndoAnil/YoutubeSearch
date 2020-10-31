package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class MainActivity extends AppCompatActivity {

    EditText text;
    RecyclerView recyclerView;
    YoutubeAdapter youtubeAdapter;
    private static final String TAG = "MainActivity";
    ShimmerLayout shimmerLayout;
    LinearLayout internetConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(EditText) findViewById(R.id.youtubeSearch);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shimmerLayout=(ShimmerLayout)findViewById(R.id.shimmerLayout);
        internetConnection=(LinearLayout)findViewById(R.id.internetConnection);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              if(text.getText().toString().length()>=3)
              {
                  String in=text.getText().toString();
                  getYoutubeData(in);
              }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        String t="Mumbai";
        getYoutubeData(t);

    }

    public void getYoutubeData(String str)
    {
        if(!isNetworkConnected())
        {
            internetConnection.setVisibility(View.VISIBLE);
            shimmerLayout.setVisibility(View.GONE);
        }
        else
        {
        shimmerLayout.startShimmerAnimation();
        shimmerLayout.setVisibility(View.VISIBLE);
        final List<Youtube_data> list = new ArrayList<>();
        Log.d(TAG, "getYoutubeData: started");
        String url="https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=50&key=AIzaSyB0kyxtIUa_T-sKcI2ohZu3B122O7mcFxk&type=video&q="+str;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject res=new JSONObject(response);
                    JSONArray jsonArray=res.getJSONArray("items");
                    for(int i=1;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        JSONObject jsonObject1=jsonObject.getJSONObject("snippet");
                        String title=jsonObject1.getString("title");
                        String chaltitle=jsonObject1.getString("channelTitle");
                        String time=jsonObject1.getString("publishTime");
                        JSONObject thumb=jsonObject1.getJSONObject("thumbnails");
                        JSONObject thumb1=thumb.getJSONObject("high");
                        String thumbnail=thumb1.getString("url");
                        list.add(new Youtube_data(thumbnail,title,chaltitle,time));
                        shimmerLayout.setVisibility(View.INVISIBLE);
                    }

                    youtubeAdapter=new YoutubeAdapter(list,MainActivity.this);
                    recyclerView.setAdapter(youtubeAdapter);
                    youtubeAdapter.notifyDataSetChanged();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
       RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
       requestQueue.add(stringRequest);
        }
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}