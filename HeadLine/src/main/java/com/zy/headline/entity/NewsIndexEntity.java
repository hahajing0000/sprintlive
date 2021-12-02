package com.zy.headline.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.headline.entity
 * @ClassName: NewsIndexEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/2 8:11
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/2 8:11
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NewsIndexEntity {

    public List<NewsIndexInfo> data=new ArrayList<>();

    public static class NewsIndexInfo{
        private String type;
        private String id;
        private String column;
        private String needUpdate;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getNeedUpdate() {
            return needUpdate;
        }

        public void setNeedUpdate(String needUpdate) {
            this.needUpdate = needUpdate;
        }
    }
}
