package com.nbasprint.net.retrofit.common;

import org.reactivestreams.Subscription;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.nbasprint.net.retrofit.common
 * @ClassName: FlowableSubscriber
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/6 8:54
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/6 8:54
 * @UpdateRemark:
 * @Version: 1.0
 */
public class FlowableSubscriber<T> implements io.reactivex.FlowableSubscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(T o) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
