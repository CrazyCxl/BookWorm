package com.cxl.manager;

import android.util.Log;

import com.cxl.bookbase.Book;
import com.cxl.bookbase.WebsiteInfo;

public class WebVisiter {
    public interface WebVisitoerListener {
        // TODO: Update argument type and name
        void onBookSearched(Book book);
    }

    private WebVisitoerListener listener;
    private final String LOG_TAG = "WebVisiter LOG:";

    public WebVisiter(WebVisitoerListener t_listener){
        listener = t_listener;
    }

    public void searchBooks(String name) {
        Log.i(LOG_TAG,"start search "+name);
        Book b = new Book();
        b.setName(name);
        WebsiteInfo info = new WebsiteInfo();
        info.setName("bqg");
        b.setWebsitInfo(info);
        listener.onBookSearched(b);
    }
}
