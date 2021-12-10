package com.zg6.log;

import com.zg6.log.common.LoggerLevel;
import com.zg6.log.common.LoggerType;
import com.zg6.log.impl.LogcatLoggerImpl;

import java.util.PropertyResourceBundle;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zg6.log
 * @ClassName: Logger
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/10 10:55
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/10 10:55
 * @UpdateRemark:
 * @Version: 1.0
 */
public class Logger {
//    private static Logger instance=null;
//    private Logger(){}
//    static class HANDLER{
//        static Logger INSTANCE=new Logger();
//    }
//
//    public static Logger getInstance(){
//        return HANDLER.INSTANCE;
//    }
    private ILogger logger;

    private boolean isDebug=true;
    private String tag="";
    private LoggerLevel level=LoggerLevel.Verbose;
    private String saveUrl="";
    private LoggerType type=LoggerType.LOGCAT;

    public Logger(boolean isDebug, String tag, LoggerLevel level, String url,LoggerType type) {
//        this.isDebug=isDebug;
//        this.tag=tag;
//        this.level=level;
//        this.saveUrl=url;
        switch (type){
            case EMAIL:
                throw new RuntimeException("Method not implements");
            case LOGCAT:
                logger=new LogcatLoggerImpl();
                break;
            case DB:
                throw new RuntimeException("Method not implements");
            case FILE:
                throw new RuntimeException("Method not implements");
            case SERVER:
                throw new RuntimeException("Method not implements");
            default:
                logger=new LogcatLoggerImpl();
        }

        logger.setDebug(isDebug);
        logger.setLogTag(tag);
        logger.setLogLevel(level);
        logger.setSaveUrl(url);
    }

    public void d(String tag,String log){
        logger.d(tag,log);
    }
    public void w(String tag,String log){
        logger.w(tag,log);
    }
    public void i(String tag,String log){
        logger.i(tag,log);
    }
    public void e(String tag,String log){
        logger.e(tag,log);
    }
    void v (String tag,String log){
        logger.v(tag,log);
    }


    public static class Builder{
        private boolean isDebug=true;
        private String tag="";
        private LoggerLevel level=LoggerLevel.Verbose;
        private String saveUrl="";
        private LoggerType loggerType=LoggerType.LOGCAT;

        public Builder setDebug(boolean debug) {
            this.isDebug = debug;
            return this;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder setLevel(LoggerLevel level) {
            this.level = level;
            return this;
        }

        public Builder setSaveUrl(String saveUrl) {
            this.saveUrl = saveUrl;
            return this;
        }

        public Builder setLoggerType(LoggerType loggerType) {
            this.loggerType = loggerType;
            return this;
        }

        public Logger build(){
            return new Logger(isDebug,tag,level,saveUrl,loggerType);
        }
    }
}
