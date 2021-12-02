package com.nbasprint.net.entity;

import java.io.Serializable;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.headline.entity
 * @ClassName: BaseEntity
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/2 8:10
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/2 8:10
 * @UpdateRemark:
 * @Version: 1.0
 */
public class BaseEntity{

    private int code;
    private String version;

    public BaseEntity() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
