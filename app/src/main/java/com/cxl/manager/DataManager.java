package com.cxl.manager;

import com.cxl.bookbase.Book;
import com.cxl.webbase.BQGWebsite;
import com.cxl.webbase.Website;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static List<Website> getSupportWebsites(){
        List<Website> sites = new ArrayList<>();
        BQGWebsite bqgWebsite = new BQGWebsite();
        sites.add(bqgWebsite);
        return sites;
    }
}
