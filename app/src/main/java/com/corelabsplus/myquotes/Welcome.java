package com.corelabsplus.myquotes;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class Welcome extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 5000;
    private Context context;

    @BindView(R.id.dailyQuote)TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        context = this;

        getDailyQuote();


    }

    // Generate daily quote

    public void getDailyQuote(){
        final String url = "http://quotes.rest/qod.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject contents = jsonObject.getJSONObject("contents");
                    JSONArray quotesArray = contents.getJSONArray("quotes");

                    String quoteQ = quotesArray.getJSONObject(0).getString("quote");
                    String author = quotesArray.getJSONObject(0).getString("author");

                    Quote quote = new Quote(quoteQ, author);

                    textView.setText(quoteQ);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Welcome.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },SPLASH_TIMEOUT);

                } catch (JSONException e) {
                    //e.printStackTrace();

                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //makeToast(error.getMessage(), 1);
                getDailyQuote();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }

    //FUNCTION TO MAKE TOAST

    public void makeToast(String message, int length){
        Toast.makeText(context, message, length).show();
    }


}
