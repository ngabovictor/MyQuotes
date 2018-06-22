package com.corelabsplus.myquotes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.loading) RelativeLayout loadingIndicator;

    private Context context;
    private List<Quote> quotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



//        GETTING QUOTES FROM NET
        generateRandomQuotes();
    }

    private void generateRandomQuotes() {

        final String url = "https://gist.githubusercontent.com/nasrulhazim/54b659e43b1035215cd0ba1d4577ee80/raw/e3c6895ce42069f0ee7e991229064f167fe8ccdc/quotes.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                loadingIndicator.setVisibility(View.INVISIBLE);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray quotesArray = jsonObject.getJSONArray("quotes");

                    for (int i = 0; i < quotesArray.length(); i++) {
                        JSONObject quoteResponse = quotesArray.getJSONObject(i);
                        Quote quote = new Quote(quoteResponse.getString("quote"), quoteResponse.getString("author"));

                        quotes.add(quote);
                    }

                    QuotesAdapter adapter = new QuotesAdapter(context, quotes);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    makeToast(e.getMessage(), 1);
                }
            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //makeToast(error.getMessage(), 1);
                    generateRandomQuotes();
                }
            }
        );

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);

    }


    //FUNCTION TO MAKE TOAST

    public void makeToast(String message, int length){
        Toast.makeText(context, message, length).show();
    }
}
