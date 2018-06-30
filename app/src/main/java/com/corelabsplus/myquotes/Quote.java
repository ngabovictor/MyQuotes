package com.corelabsplus.myquotes;

public class Quote {

    private String quote, author;

    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toString(){

        String plainQuote = "\"" + quote + "\"" + "\n" + "~" + author;

        return plainQuote;
    }
}
