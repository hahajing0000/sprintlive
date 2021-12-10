package com.baweigame.sprintnba.utils;

import com.baweigame.sprintnba.BuildConfig;
import com.zg6.log.Logger;
import com.zg6.log.common.LoggerLevel;
import com.zg6.log.common.LoggerType;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.baweigame.sprintnba.utils
 * @ClassName: LoggerUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/10 13:58
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/10 13:58
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LoggerUtils {
    private static LoggerUtils instance=null;
    private final Logger logger;

    private LoggerUtils(){

        logger = new Logger.Builder()
                .setTag("456")
                .setDebug(BuildConfig.isDebug)
                .setLoggerType(LoggerType.LOGCAT)
                .setLevel(LoggerLevel.Debug)
                .build();
    }
    static class HOLDER{
        static LoggerUtils INSTANCE=new LoggerUtils();
    }

    public static LoggerUtils getInstance(){
        return HOLDER.INSTANCE;
    }

    public Logger getLogger(){
        return logger;
    }
}
