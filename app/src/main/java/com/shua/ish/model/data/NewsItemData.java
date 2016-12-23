package com.shua.ish.model.data;

import com.shua.ish.model.data.entity.NewsItemList;

import java.util.List;

/**
 * NewsItem
 * Created by SHUA on 2016/6/24.
 */
public class NewsItemData{
    private int total;
    private long serverTime;

    private List<NewsItemList> itemList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public List<NewsItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<NewsItemList> itemList) {
        this.itemList = itemList;
    }
}
