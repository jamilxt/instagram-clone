package com.jamilxt.instagram_clone.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_photo")
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long photoId;
    @Column(name = "url")
    private String url;
    @Column(name = "caption")
    private String caption;

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
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

    @Override
    public String toString() {
        return "Photo{" +
                "photoId=" + photoId +
                ", url='" + url + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}
