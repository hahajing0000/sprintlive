package com.zg6.log.impl;

import android.text.TextUtils;
import android.util.Log;

import com.zg6.log.ILogger;
import com.zg6.log.common.LoggerLevel;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zg6.log.impl
 * @ClassName: LogcatLoggerImpl
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/10 10:47
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/10 10:47
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LogcatLoggerImpl implements ILogger {
    private static String NORMAL_TAG="123";
    boolean isDebug=true;
    String mTag="";
    LoggerLevel loggerLevel=LoggerLevel.Verbose;

    public LogcatLoggerImpl(){

    }

    @Override
    public void d(String tag, String log) {
        if (isDebug&&loggerLevel.ordinal()>=LoggerLevel.Debug.ordinal()){
            Log.d(TextUtils.isEmpty(mTag)?NORMAL_TAG:mTag, String.format("%s --> %s",tag,log));
        }

    }

    @Override
    public void w(String tag, String log) {
        if (isDebug&&loggerLevel.ordinal()>=LoggerLevel.Warnning.ordinal()){
            Log.w(TextUtils.isEmpty(mTag)?NORMAL_TAG:mTag, String.format("%s --> %s",tag,log));
        }

    }

    @Override
    public void i(String tag, String log) {
        if (isDebug&&loggerLevel.ordinal()>=LoggerLevel.Info.ordinal()){
            Log.i(TextUtils.isEmpty(mTag)?NORMAL_TAG:mTag, String.format("%s --> %s",tag,log));
        }

    }

    @Override
    public void e(String tag, String log) {
        if (isDebug&&loggerLevel.ordinal()>=LoggerLevel.Error.ordinal()){
            Log.e(TextUtils.isEmpty(mTag)?NORMAL_TAG:mTag, String.format("%s --> %s",tag,log));
        }

    }
    //test
    @Override
    public void v(String tag, String log) {
        if (isDebug&&loggerLevel.ordinal()>=LoggerLevel.Verbose.ordinal()){
            Log.v(TextUtils.isEmpty(mTag)?NORMAL_TAG:tag, String.format("%s --> %s",tag,log));
        }

    }

    @Override
    public void setDebug(boolean isDebug) {
        this.isDebug=isDebug;
    }

    @Override
    public void setLogTag(String tag) {
        this.mTag=tag;
    }

    @Override
    public void setLogLevel(LoggerLevel level) {
        this.loggerLevel=level;
    }

    @Override
    public void setSaveUrl(String url) {

    }
}
