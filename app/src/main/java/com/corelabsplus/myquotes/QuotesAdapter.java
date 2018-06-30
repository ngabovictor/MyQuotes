package com.corelabsplus.myquotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {

    private Context context;
    private List<Quote> quotes;

    public QuotesAdapter(Context context, List<Quote> quotes) {
        this.context = context;
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quote_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Quote quote = quotes.get(position);

        String quoteQ, author;

        quoteQ = quote.getQuote();
        author = quote.getAuthor();

        holder.quoteView.setText("\"" + quoteQ + "\"");
        holder.authorView.setText("~" + author);

        //HANDLING COPY AND SHARE

        holder.copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do copy here
                // Remember to use .toString method of the Quote class
            }
        });

        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do share here
                // Remember to use .toString method of the Quote class
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.quote_view) TextView quoteView;
        @BindView(R.id.author_view) TextView authorView;
        @BindView(R.id.share_btn) ImageView shareBtn;
        @BindView(R.id.copy_btn) ImageView copyBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
