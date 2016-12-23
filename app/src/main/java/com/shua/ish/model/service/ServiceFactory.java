package com.shua.ish.model.service;

import com.shua.ish.model.api.ImageApi;
import com.shua.ish.model.api.NewsApi;
import com.shua.ish.model.api.VideosApi;

/**
 * Service 工厂
 */

public class ServiceFactory {
    public static final Object monitor = new Object();

    private static ImageApi mMeiZiService = null;
    private static VideosApi mVideoService = null;

    private static NewsApi mNewsService = null;


    public static ImageApi getMeiZiService() {
        synchronized (monitor) {
            if (mMeiZiService == null) {
                mMeiZiService = new ServiceManager().getMeiZiService();
            }
            return mMeiZiService;
        }
    }

    public static VideosApi getVideoService() {
        synchronized (monitor) {
            if (mVideoService == null) {
                mVideoService = new ServiceManager().getVideoService();
            }
            return mVideoService;
        }
    }

    public static NewsApi getmNewsBannerService() {
        synchronized (monitor) {
            if (mNewsService == null) {
                mNewsService = new ServiceManager().getNewsService();
            }
            return mNewsService;
        }
    }
}
