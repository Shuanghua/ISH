package com.shua.ish.model.data.entity;

import io.realm.RealmObject;

/**
 * Item 实体数据
 * Created by SHUA on 2016/6/28.
 */
public class NewsItemList extends RealmObject{
    private String detailUrl;
    private String itemID;
    private String itemType;
    private String photoCount;
    private String operate_time;
    private String itemTitle;
    /**
     * imgUrl1 : http://p1.img.cctvpic.com/photoworkspace/2016/06/26/2016062615140431913.jpg
     * imgUrl3 : http://p1.img.cctvpic.com/photoworkspace/2016/06/26/2016062615140431913.jpg
     * imgUrl2 : http://p1.img.cctvpic.com/photoworkspace/2016/06/26/2016062615140431913.jpg
     */

    private NewsItemImage itemImage;

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(String photoCount) {
        this.photoCount = photoCount;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public NewsItemImage getItemImage() {
        return itemImage;
    }

    public void setItemImage(NewsItemImage itemImage) {
        this.itemImage = itemImage;
    }
}
