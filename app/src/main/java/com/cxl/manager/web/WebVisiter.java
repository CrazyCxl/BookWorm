package com.cxl.manager.web;

import android.util.Log;

import com.cxl.bookbase.Book;
import com.cxl.bookbase.WebsiteInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebVisiter {
    public interface WebVisitoerListener extends SearchBooksRunable.SearchBooksRunableListener {
        // TODO: Update argument type and name
    }

    private WebVisitoerListener listener;
    private final String LOG_TAG = "WebVisiter LOG:";

    public WebVisiter(WebVisitoerListener t_listener){
        listener = t_listener;
    }

    public void searchBooks(String name) {
        Log.i(LOG_TAG,"start search "+name);
        new Thread(new SearchBooksRunable(name,listener)).start();
    }
}
