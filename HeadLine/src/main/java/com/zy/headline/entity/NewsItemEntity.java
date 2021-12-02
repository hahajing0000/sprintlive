package com.zy.headline.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.headline.entity
 * @ClassName: NewsItemEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/2 9:06
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/2 9:06
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NewsItemEntity {

    public LinkedHashMap<String,NewsItemInfo> data=new LinkedHashMap<>();

    public static class NewsItemInfo{
        private String newsId;
        private String title;
        @SerializedName("abstract")
        private String abstractX;
        private String url;
        private String imgurl;
        private String imgurl1;
        private String imgurl2;
        private String pub_time;
        private String publishTime;
        private String currentTime;
        private String pub_time_new;
        private String pub_time_detail;
        private String atype;
        private String atypeName;
        private String newsAppId;
        private String source;
        private String commentsNum;
        private String commentId;

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl1() {
            return imgurl1;
        }

        public void setImgurl1(String imgurl1) {
            this.imgurl1 = imgurl1;
        }

        public String getImgurl2() {
            return imgurl2;
        }

        public void setImgurl2(String imgurl2) {
            this.imgurl2 = imgurl2;
        }

        public String getPub_time() {
            return pub_time;
        }

        public void setPub_time(String pub_time) {
            this.pub_time = pub_time;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }

        public String getPub_time_new() {
            return pub_time_new;
        }

        public void setPub_time_new(String pub_time_new) {
            this.pub_time_new = pub_time_new;
        }

        public String getPub_time_detail() {
            return pub_time_detail;
        }

        public void setPub_time_detail(String pub_time_detail) {
            this.pub_time_detail = pub_time_detail;
        }

        public String getAtype() {
            return atype;
        }

        public void setAtype(String atype) {
            this.atype = atype;
        }

        public String getAtypeName() {
            return atypeName;
        }

        public void setAtypeName(String atypeName) {
            this.atypeName = atypeName;
        }

        public String getNewsAppId() {
            return newsAppId;
        }

        public void setNewsAppId(String newsAppId) {
            this.newsAppId = newsAppId;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getCommentsNum() {
            return commentsNum;
        }

        public void setCommentsNum(String commentsNum) {
            this.commentsNum = commentsNum;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }
    }
}
