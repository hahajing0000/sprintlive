package com.zy.headline.common;

import java.io.Serializable;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.headline.common
 * @ClassName: Constant
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/1 15:06
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/1 15:06
 * @UpdateRemark:
 * @Version: 1.0
 */
public class Constant {
    /**
     * 新闻类型
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/12/1 15:22
     */ 
    public enum NewsType implements Serializable {

        BANNER("banner"),      //头条
        NEWS("news"),          //新闻
        VIDEO("videos"),     //视频集锦
        DEPTH("depth"),        //十佳球/五佳球
        HIGHLIGHT("highlight");//赛场花絮

        String type;

        NewsType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
