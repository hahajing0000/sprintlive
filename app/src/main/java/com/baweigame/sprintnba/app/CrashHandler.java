package com.baweigame.sprintnba.app;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;

import com.baweigame.common.tools.LogUtils;
import com.baweigame.common.tools.ToastUtils;

import androidx.annotation.NonNull;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.sprintnba.app
 * @ClassName: CrashHandler
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/29 9:34
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/29 9:34
 * @UpdateRemark:
 * @Version: 1.0
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private String TAG = CrashHandler.class.getSimpleName();
    private CrashHandler(){}
    static class HOLDER{
        public static CrashHandler INSTANCE=new CrashHandler();
    }

    public static CrashHandler getInstance(){
        return HOLDER.INSTANCE;
    }

    public void init(Context context){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                ToastUtils.showToast("哎呀，程序发生异常啦...");
                Looper.loop();
            }
        }).start();

        SystemClock.sleep(3 * 1000);
        LogUtils.getInstance().e(TAG, "CrashHandler.InterruptedException--->" + e.toString());
        //退出程序
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
