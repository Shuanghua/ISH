package com.shua.ish.model.api;


import com.shua.ish.model.data.ImageData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ImageApi {

    @GET("197-1")
    Observable<ImageData> getImageData(@Query("num") String num,
                                       @Query("page") String page,
                                       @Query("showapi_appid") String showapi_appid,
                                       @Query("showapi_sign") String showapi_sign);
}
