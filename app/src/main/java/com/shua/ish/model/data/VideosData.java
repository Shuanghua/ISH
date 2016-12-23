package com.shua.ish.model.data;

import com.shua.ish.model.data.entity.VideoInfo;

import java.util.List;

/**
 * Created by SHUA on 2016/6/12.
 */
public class VideosData {
    private VideosDataBean data;

    public VideosDataBean getData() {
        return data;
    }

    public void setData(VideosDataBean data) {
        this.data = data;
    }

    public static class VideosDataBean {

        private List<VideosFeedBean> feed;

        public List<VideosFeedBean> getFeed() {
            return feed;
        }

        public void setFeed(List<VideosFeedBean> feed) {
            this.feed = feed;
        }

        public static class VideosFeedBean {
            private String title;

            private VideoInfo videoInfo;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public VideoInfo getVideoInfo() {
                return videoInfo;
            }

            public void setVideoInfo(VideoInfo videoInfo) {
                this.videoInfo = videoInfo;
            }
        }
    }
}
