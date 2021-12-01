package com.nbasprint.net.retrofit.calladapter;

import android.os.Looper;

import java.lang.reflect.Type;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.net.retrofit.calladapter
 * @ClassName: LiveDataCallAdapter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/16 10:55
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/16 10:55
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<R>> {

    private Type type;
    public LiveDataCallAdapter(Type _t){
        this.type=_t;
    }

    @Override
    public Type responseType() {
        return this.type;
    }

    /**
     * adapt - OkHttp  因为 Retrofit 网络请求能力？ CallFactory
     * @param
     * @return
     * @author zhangyue
     * @time 2021/11/16 10:58
     */
    @Override
    public LiveData<R> adapt(Call<R> call) {
        final MutableLiveData<R> liveData=new MutableLiveData<>();

        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    liveData.setValue(response.body());
                }
                else{
                    liveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    liveData.setValue(null);
                }
                else{
                    liveData.postValue(null);
                }
            }
        });
        return liveData;
    }
}
