package com.knoxtech.msbtepapersforischeme;

import android.net.Uri;

public class Note {

    String title,url;

    public Note() {
    }

    public Note(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
