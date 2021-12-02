package com.zy.headline.entity;

import com.google.gson.annotations.SerializedName;
import com.nbasprint.net.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.headline.entity
 * @ClassName: NewsDetailEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/2 10:57
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/2 10:57
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NewsDetailEntity extends BaseEntity {

    private NewsDetailInfo data;

    public NewsDetailInfo getData() {
        return data;
    }

    public void setData(NewsDetailInfo data) {
        this.data = data;
    }

    public static class NewsDetailInfo{
        public String title;
        @SerializedName("abstract")
        public String abstractX;
        public List<NewsDetailContentEntity> content;

        public String url;
        public String imgurl;
        public String imgurl1;
        public String imgurl2;
        public String time;
        public String atype;
        public String commentId;
        public String newsAppId;
    }

    public static class NewsDetailContentEntity{

        /**
         * info : NBA官网&APP内测用户邀请函
         * type : text
         */

        private String info;
        private String type;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
