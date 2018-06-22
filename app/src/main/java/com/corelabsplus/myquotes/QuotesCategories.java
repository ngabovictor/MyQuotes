package com.corelabsplus.myquotes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import butterknife.BindView;


public class QuotesCategories extends AppCompatActivity {

    @BindView(R.id.category) Button category;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_categories);
        context = this;

        getCategories();

    }

    //get and list quotes categories

    public void getCategories(){

    }
}
