package com.cxl.manager.web;

import android.util.Log;

import com.cxl.webbase.Website;

public class WebVisiter {

    private Website.WebsiteListener listener;
    private final String LOG_TAG = "WebVisiter LOG:";

    public WebVisiter(Website.WebsiteListener t_listener){
        listener = t_listener;
    }

    public void searchBooks(String name) {
        Log.i(LOG_TAG,"start search "+name);
        new Thread(new SearchBooksRunable(name,listener)).start();
    }
}
