package com.cxl.manager.web;

import android.util.Log;

import com.cxl.bookbase.Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SearchBooksRunable implements Runnable {

    public interface SearchBooksRunableListener {
        // TODO: Update argument type and name
        void onBookSearched(Book book);
    }

    private String key;
    private SearchBooksRunableListener listener;
    private final String LOG_TAG = "SearchBooksRunable LOG";

    public SearchBooksRunable(String key_t,SearchBooksRunableListener listener){
        this.key = key_t;
        this.listener = listener;
    }

    @Override
    public void run() {
        try
        {
            String url = "https://www.boquge.com/search.htm?keyword="+key;
            Document document = Jsoup.connect(url).get();
            Elements lis = document.select("li.list-group-item.clearfix");
            if(lis.size() > 1){
                lis.remove(0);
                for(Element li : lis){
                    Element book_div = li.select("div.col-xs-3").last();
                    Element book_herf = book_div.select("a").first();
                    String str_book_url = book_herf.attr("abs:href");
                    Log.i(LOG_TAG,"select "+book_div.text()+" "+str_book_url);
                }
            }else{
                Log.i(LOG_TAG,"select null in "+url);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
