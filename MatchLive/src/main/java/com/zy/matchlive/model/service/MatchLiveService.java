package com.zy.matchlive.model.service;

import android.app.ZygotePreload;

import com.nbasprint.net.RetrofitFactory;
import com.nbasprint.net.common.ServerResultException;
import com.zy.matchlive.entity.LiveDetailEntity;
import com.zy.matchlive.entity.LiveIndexEntity;
import com.zy.matchlive.entity.MatchBaseEntity;
import com.zy.matchlive.entity.MatchCalendarEntity;
import com.zy.matchlive.entity.MatchStatEntity;
import com.zy.matchlive.entity.MatchVideoEntity;
import com.zy.matchlive.entity.MatchsEntity;
import com.zy.matchlive.model.api.MatchLiveApi;

import org.reactivestreams.Subscription;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.matchlive.model.service
 * @ClassName: MatchLiveService
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/6 7:51
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/6 7:51
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MatchLiveService {
    private MatchLiveApi api;

    public MatchLiveService(){
        api= RetrofitFactory.getInstance().create(MatchLiveApi.class);
    }

    public LiveData<MatchCalendarEntity> getMatchCalendar(int teamId, int year, int month) {
        return api.getMatchCalendar(teamId,year,month);
    }

    public LiveData<MatchsEntity> getMatchsByData(String date) {
        return api.getMatchsByData(date);
    }

    // baseInfo?mid=100000:1468573
    public LiveData<MatchBaseEntity> getMatchBaseInfo(String mid) {
        return api.getMatchBaseInfo(mid);
    }

    /**
     *
     * @param
     * mid
     * tabType 1：比赛数据  2：技术统计  3：比赛前瞻
     * @return
     * @author zhangyue
     * @time 2021/12/6 13:28
     */
    public LiveData<MatchStatEntity> getMatchStat(String mid, String tabType) {
        return api.getMatchStat(mid, tabType);
    }

    /**
     * 获取赛事直播数据
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/6 13:39
     */ 
    public LiveData<MatchVideoEntity> getMatchVideo(String mid) {
        return api.getMatchVideo(mid);
    }

    // baseInfo?mid=100000:1468573
    /**
     * 获取直播索引
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/6 13:40
     */ 
    public LiveData<LiveIndexEntity> getMatchLiveIndex(String mid) {
        return api.getMatchLiveIndex(mid);
    }

    /**
     * 获取直播详情数据
     * @param
     * ids “获取直播索引”方法获取到的id集合 多个用逗号分隔
     * @return
     * @author zhangyue
     * @time 2021/12/6 13:41
     */ 
    public LiveData<LiveDetailEntity> getMatchLiveDetail(String mid, String ids) {
        return api.getMatchLiveDetail(mid, ids);
    }
}
