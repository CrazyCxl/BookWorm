package com.cxl.manager.web;

import android.util.Log;

import com.cxl.bookbase.Book;
import com.cxl.manager.DataManager;
import com.cxl.webbase.Website;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class SearchBooksRunable implements Runnable {

    public interface SearchBooksRunableListener {
        // TODO: Update argument type and name
        void onBookSearched(Book book);
    }

    private String key;
    private Website.WebsiteListener listener;
    private final String LOG_TAG = "SearchBooksRunable LOG";

    public SearchBooksRunable(String key_t,Website.WebsiteListener listener){
        this.key = key_t;
        this.listener = listener;
    }

    @Override
    public void run() {
        List<Website> websites = DataManager.getSupportWebsites();
        for(Website website:websites){
            website.startSearchBooks(listener,key);
        }
    }
}
