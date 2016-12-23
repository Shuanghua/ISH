package com.shua.ish.model.data;

import com.shua.ish.model.data.entity.NewsBigImg;

import java.util.List;

/**
 * Created by SHUA on 2016/6/24.
 */
public class NewsBannerData {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String listUrl;

        private List<NewsBigImg> bigImg;

        public String getListUrl() {
            return listUrl;
        }

        public void setListUrl(String listUrl) {
            this.listUrl = listUrl;
        }

        public List<NewsBigImg> getBigImg() {
            return bigImg;
        }

        public void setBigImg(List<NewsBigImg> bigImg) {
            this.bigImg = bigImg;
        }

    }
}
