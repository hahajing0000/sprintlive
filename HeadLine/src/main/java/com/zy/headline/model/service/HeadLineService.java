package com.zy.headline.model.service;

import com.nbasprint.net.RetrofitFactory;
import com.zy.headline.common.Constant;
import com.nbasprint.net.entity.BaseEntity;
import com.zy.headline.entity.NewsDetailEntity;
import com.zy.headline.entity.NewsIndexEntity;
import com.zy.headline.entity.NewsItemEntity;
import com.zy.headline.model.api.HeadLineApi;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.headline.model.service
 * @ClassName: HeadLineService
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/1 15:07
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/1 15:07
 * @UpdateRemark:
 * @Version: 1.0
 */
public class HeadLineService {
    private HeadLineApi api=RetrofitFactory.getInstance().create(HeadLineApi.class);
    /**
     * 获取资讯索引列表
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/2 8:09
     */ 
    public LiveData<NewsIndexEntity> getNewsIndex(Constant.NewsType newsType){
        return api.getNewsIndex(newsType.getType());
    }
    
    /**
     * 根据索引获取资讯信息
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/12/2 8:12
     */ 
    public LiveData<NewsItemEntity> getNewsItem(Constant.NewsType newsType, String articleIds){
        return api.getNewsItem(newsType.getType(),articleIds);
    }


    /**
     * 根据索引获取资讯详情信息
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/2 10:58
     */ 
    public LiveData<NewsDetailEntity> getNewsDetail(Constant.NewsType newsType, String articleId){
        return api.getNewsDetail(newsType.getType(),articleId);
    }
}
