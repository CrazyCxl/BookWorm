package com.cxl.webbase;

import com.cxl.bookbase.Book;

public abstract class Website {
    enum WebSiteType{
        BQG_WEBSITE
    }

    public abstract String getName();
    public abstract WebSiteType id();
    public abstract void startSearchBooks(WebsiteListener listener,String key);

    public interface WebsiteListener{
        void onBookSearched(Book book);
    }
}
