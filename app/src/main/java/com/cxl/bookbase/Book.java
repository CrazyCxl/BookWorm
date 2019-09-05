package com.cxl.bookbase;

import com.cxl.webbase.Website;

public class Book {
    private String name;
    private Website websit;

    public Book(String name, Website websit){
        this.name = name;
        this.websit = websit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Website getWebsit() {
        return websit;
    }

    public void setWebsit(Website websit) {
        this.websit = websit;
    }
}
