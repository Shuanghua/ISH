package com.shua.ish.model.api;

import com.shua.ish.model.data.NewsBannerData;
import com.shua.ish.model.data.NewsItemData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;


public interface NewsApi {

    @GET("index.php?controller=list&action=getHandDataInfoNew&handdata_id=TDAT1372928688333145&n1=3&n2=20&toutuNum=3")
    Observable<NewsBannerData> getData();

    @GET
    Observable<NewsItemData> getNewsItemData(@Url String url);

    @GET("index.php?controller=list&action=getHandDataListInfoNew&handdata_id=TDAT1372928688333145")
    Observable<NewsItemData> loadMoreData(@Query("toutuNum") String toutuNum);
}
