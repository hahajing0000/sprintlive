package com.baweigame.common.tools;

import com.baweigame.common.BuildConfig;
import com.zy.logger.Logger;
import com.zy.logger.common.LoggerLevel;
import com.zy.logger.common.LoggerType;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.common.tools
 * @ClassName: LogUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/29 9:37
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/29 9:37
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LogUtils {

    private String TAG="BAWEI->GAME❀";
    private final Logger logger;

    private static class HOLDER{
        public static LogUtils INSTANCE=new LogUtils();
    }

    private LogUtils(){
        logger = new Logger.Builder().setLoggerType(LoggerType.LOGCAT)
                .setLevel(LoggerLevel.Verbose)
                .setDebug(BuildConfig.DEBUG)
                .setTAG(TAG)
                .build();
    }

    public static LogUtils getInstance(){
        return HOLDER.INSTANCE;
    }

    public  void d(String tag,String log){
        logger.d(tag,log);
    }

    public  void v(String tag,String log){
        logger.v(tag,log);
    }

    public  void w(String tag,String log){
        logger.w(tag,log);
    }

    public  void e(String tag,String log){
        logger.e(tag,log);
    }

    public  void i(String tag,String log){
        logger.i(tag,log);
    }
}
