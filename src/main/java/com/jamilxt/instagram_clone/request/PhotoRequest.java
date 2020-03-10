package com.jamilxt.instagram_clone.request;

import java.io.Serializable;

public class PhotoRequest implements Serializable {
    String url;
    String caption;

    public PhotoRequest(String url, String caption) {
        this.url = url;
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
