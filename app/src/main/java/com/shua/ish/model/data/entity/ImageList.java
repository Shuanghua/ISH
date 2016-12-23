package com.shua.ish.model.data.entity;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Image
 * Created by SHUA on 2016/6/2.
 */
public class ImageList extends RealmObject {

    private String ctime;
    @Ignore
    private String description;

    private String picUrl;

    private String title;

    @Ignore
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
