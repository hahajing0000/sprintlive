package com.nbasprint.net.retrofit.common;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.nbasprint.net.retrofit.common
 * @ClassName: FlowableUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/6 8:50
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/6 8:50
 * @UpdateRemark:
 * @Version: 1.0
 */
public class FlowableUtils {
    public static <T> void doFlowableTask(Flowable<T> task,FlowableSubscriber subscriber){
        task.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
