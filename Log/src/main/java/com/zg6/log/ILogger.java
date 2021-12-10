package com.zg6.log;

import com.zg6.log.common.LoggerLevel;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zg6.log
 * @ClassName: ILogger
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/10 10:34
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/10 10:34
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface ILogger {
    void d(String tag,String log);
    void w(String tag,String log);
    void i(String tag,String log);
    void e(String tag,String log);
    void v (String tag,String log);

    /**
     * 是否Debug ture-打开log false-关闭log
     * @param
     * @return
     * @author zhangyue
     * @time 2021/12/10 10:46
     */
    void setDebug(boolean isDebug);
    /**
     * 日志tag标记
     * @param
     * @return
     * @author zhangyue
     * @time 2021/12/10 10:46
     */
    void setLogTag(String tag);
    /**
     * 设置日志输出等级
     * @param
     * @return
     * @author zhangyue
     * @time 2021/12/10 10:46
     */
    void setLogLevel(LoggerLevel level);
    /**
     * url 比如：本地路径 远程地址 Email
     * @param
     * @return
     * @author zhangyue
     * @time 2021/12/10 10:47
     */
    void setSaveUrl(String url);
}
