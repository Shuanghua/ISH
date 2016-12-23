package com.shua.ish.model.api;

import com.shua.ish.model.data.VideosData;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by SHUA on 2016/6/8.
 */
public interface VideosApi {

    @GET("?resource=feed")
    Observable<VideosData> getVideoData(@Query("channel") String channel,
                                        @Query("pullDirection") String pullDirection,
                                        @Query("pullTimes") String pullTimes,
                                        @Query("replacedFlag") String replacedFlag,
                                        @Query("loadingAdTimestamp") String loadingAdTimestamp,
                                        @Query("behavior") String behavior,
                                        @Query("deviceId") String deviceId,
                                        @Query("from") String from,
                                        @Query("weiboUid") String weiboUid,
                                        @Query("weiboSuid") String weiboSuid,
                                        @Query("imei") String imei,
                                        @Query("wm") String wm,
                                        @Query("chwm") String chwm,
                                        @Query("oldChwm") String oldChwm,
                                        @Query("osVersion") String osVersion,
                                        @Query("connectionType") String connectionType,
                                        @Query("resolution") String resolution,
                                        @Query("city") String city,
                                        @Query("deviceModel") String deviceModel,
                                        @QueryMap(encoded = true) Map<String, String> location,
                                        @Query("link") String link,
                                        @QueryMap(encoded = true) Map<String, String> mac,
                                        @Query("ua") String ua,
                                        @Query("urlSign") String urlSign,
                                        @Query("rand") String rand);
}
