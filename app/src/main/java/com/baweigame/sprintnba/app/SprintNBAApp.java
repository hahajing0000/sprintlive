package com.baweigame.sprintnba.app;

import android.content.pm.PackageManager;

import com.baweigame.sprintnba.BuildConfig;
import com.tencent.bugly.crashreport.CrashReport;
import com.baweigame.common.app.BaseApplication;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.sprintnba.app
 * @ClassName: SprintNBAApp
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/29 9:34
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/29 9:34
 * @UpdateRemark:
 * @Version: 1.0
 */
public class SprintNBAApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //处理未捕获异常 只是处理不直接Crash 后面的异常反馈交由Bugly处理
        CrashHandler.getInstance().init(this);

        //Bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), ConstantValue.BUGLYAPPID, BuildConfig.DEBUG);

//        try {
//            String tag = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA).metaData.getString("Log_Tag");
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
