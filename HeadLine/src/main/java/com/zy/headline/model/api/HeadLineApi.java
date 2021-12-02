package com.zy.headline.model.api;

import com.nbasprint.net.entity.BaseEntity;
import com.zy.headline.entity.NewsDetailEntity;
import com.zy.headline.entity.NewsIndexEntity;
import com.zy.headline.entity.NewsItemEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.headline.model.api
 * @ClassName: HeadLineApi
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/1 14:58
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/1 14:58
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface HeadLineApi {
    @Headers({"url_name:tecent"})
    @GET("/news/index")
    LiveData<NewsIndexEntity> getNewsIndex(@Query("column") String column);

    @Headers({"url_name:tecent"})
    @GET("/news/item")
    LiveData<NewsItemEntity> getNewsItem(@Query("column") String column, @Query("articleIds") String articleIds);

    @Headers({"url_name:tecent"})
    @GET("/news/detail")
    LiveData<NewsDetailEntity> getNewsDetail(@Query("column") String column, @Query("articleId") String articleId);

}
