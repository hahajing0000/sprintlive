package com.zy.matchlive.entity;

import com.google.gson.annotations.SerializedName;
import com.nbasprint.net.entity.BaseEntity;

import java.util.List;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.matchlive.entity
 * @ClassName: MatchVideoEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/6 13:34
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/6 13:34
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MatchVideoEntity extends BaseEntity {
    /**
     * title : 凯尔特人118-124骑士 詹皇逆天血帽遭复仇
     * abstract :
     * imgurl : http://vpic.video.qq.com/36307770/a0022ug3wuz.png
     * pub_time : 2016-12-30 12:01:42
     * vid : a0022ug3wuz
     * duration : 04:14
     * view : 624289
     * pub_time_new : 12-30 12:01
     */

    public List<VideoBean> data;

    public static class VideoBean {
        public String title;
        @SerializedName("abstract")
        public String abstractX;
        public String imgurl;
        public String pub_time;
        public String vid;
        public String duration;
        public String view;
        public String pub_time_new;
        public String realUrl;
    }
}
