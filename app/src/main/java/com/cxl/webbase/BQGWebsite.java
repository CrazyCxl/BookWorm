package com.cxl.webbase;

import android.util.Log;

import com.cxl.bookbase.Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BQGWebsite extends Website{
    private final String name = "笔趣阁";
    private final WebSiteType type = WebSiteType.BQG_WEBSITE;
    private final String LOG_TAG = "BQGWebsite LOG";
    private final String searchUrlPrefix = "https://www.boquge.com/search.htm?keyword=";

    public BQGWebsite(){
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public WebSiteType id() {
        return type;
    }

    //need run in sub thread
    @Override
    public void startSearchBooks(WebsiteListener listener, String key) {
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

                    Book book = new Book(book_div.text(),this);
                    listener.onBookSearched(book);
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
