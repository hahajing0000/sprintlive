package com.zg6.log.common;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zg6.log.common
 * @ClassName: LoggerLevel
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/10 10:31
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/10 10:31
 * @UpdateRemark:
 * @Version: 1.0
 */
public enum  LoggerLevel {
    Verbose(0),Debug(1),Info(2),Warnning(3),Error(4);

    private int value=0;
    LoggerLevel(int _value){
        value=_value;
    }
}
