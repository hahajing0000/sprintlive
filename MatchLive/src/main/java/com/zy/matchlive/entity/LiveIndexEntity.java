package com.zy.matchlive.entity;

import com.nbasprint.net.entity.BaseEntity;

import java.util.List;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.matchlive.entity
 * @ClassName: LiveIndexEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/6 13:37
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/6 13:37
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LiveIndexEntity extends BaseEntity {
    public Index data;

    public static class Index{
        public List<String> index;
    }
}
