package com.zy.matchlive.model.api;

import com.zy.matchlive.entity.LiveDetailEntity;
import com.zy.matchlive.entity.LiveIndexEntity;
import com.zy.matchlive.entity.MatchBaseEntity;
import com.zy.matchlive.entity.MatchCalendarEntity;
import com.zy.matchlive.entity.MatchStatEntity;
import com.zy.matchlive.entity.MatchVideoEntity;
import com.zy.matchlive.entity.MatchsEntity;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.matchlive.model.api
 * @ClassName: MatchLiveApi
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/4 8:28
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/4 8:28
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface MatchLiveApi {
    //calendar?teamId=27&year=2016&month=7 // 查询球队赛程
    @GET("/match/calendar")
    LiveData<MatchCalendarEntity> getMatchCalendar(@Query("teamId") int teamId, @Query("year") int year, @Query("month") int month);

    @GET("/match/listByDate")
    LiveData<MatchsEntity> getMatchsByData(@Query("date") String date);

    // baseInfo?mid=100000:1468573
    @GET("/match/baseInfo")
    LiveData<MatchBaseEntity> getMatchBaseInfo(@Query("mid") String mid);

    // stat?mid=100000:1468573&tabType=3
    @GET("/match/stat")
    LiveData<MatchStatEntity> getMatchStat(@Query("mid") String mid, @Query("tabType") String tabType);

    @GET("/video/matchVideo")
    LiveData<MatchVideoEntity> getMatchVideo(@Query("matchId") String mid);

    // baseInfo?mid=100000:1468573
    @GET("/match/textLiveIndex")
    LiveData<LiveIndexEntity> getMatchLiveIndex(@Query("mid") String mid);

    @GET("/match/textLiveDetail")
    LiveData<LiveDetailEntity> getMatchLiveDetail(@Query("mid") String mid, @Query("ids") String ids);
}
