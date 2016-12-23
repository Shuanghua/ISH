package com.shua.ish.model.service;

import com.shua.ish.app.Config;
import com.shua.ish.model.api.ImageApi;
import com.shua.ish.model.api.NewsApi;
import com.shua.ish.model.api.VideosApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiServiceManager
 */
public class ServiceManager {

    private static ImageApi mMeiZiService = null;
    private static VideosApi mVideoService = null;
    private static NewsApi mNewsService = null;

    public ServiceManager() {
        Retrofit meiZiRetrofit = new Retrofit.Builder()
                .baseUrl(Config.URL_IMAGE)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit videoRetrofit = new Retrofit.Builder()
                .baseUrl(Config.URL_VIDEO)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit newsRetrofit = new Retrofit.Builder()
                .baseUrl(Config.URL_NEWS)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMeiZiService = meiZiRetrofit.create(ImageApi.class);
        mVideoService = videoRetrofit.create(VideosApi.class);
        mNewsService = newsRetrofit.create(NewsApi.class);
    }

    public ImageApi getMeiZiService() {
        return mMeiZiService;
    }

    public VideosApi getVideoService() {
        return mVideoService;
    }

    public NewsApi getNewsService() {
        return mNewsService;
    }
}
