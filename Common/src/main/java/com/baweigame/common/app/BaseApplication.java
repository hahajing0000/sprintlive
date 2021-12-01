package com.baweigame.common.app;

import android.app.Application;
import android.content.Context;

import com.baweigame.common.tools.LogUtils;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.common.app
 * @ClassName: BaseApplication
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/29 9:44
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/29 9:44
 * @UpdateRemark:
 * @Version: 1.0
 */
public class BaseApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }
}
