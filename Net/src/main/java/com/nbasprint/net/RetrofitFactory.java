package com.nbasprint.net;

import com.baweigame.common.tools.LogUtils;
import com.nbasprint.net.okhttp.interceptor.CustomInterceptor;
import com.nbasprint.net.retrofit.calladapter.LiveDataCallAdapter;
import com.nbasprint.net.retrofit.calladapter.LiveDataCallAdapterFactory;
import com.nbasprint.net.retrofit.convertfactory.CustomGsonConverterFactory;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.nbasprint.net
 * @ClassName: RetrofitFactory
 * @Description:
 *
 * 该工具类用于处理这个项目的网络请求操作，支持如下业务处理：
 * 1、Token处理
 * 2、重试
 * 3、动态更改BaseUrl请求地址
 *      1）通过@Header注解标注要切换的地址
 *      2）拦截器根据设置Header信息完成BaseUrl的动态切换
 * 4、Cookies
 * 5、日志输出及文本内容自定义
 * 6、LiveData的自定义回调工厂
 *
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/1 13:04
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/1 13:04
 * @UpdateRemark:
 * @Version: 1.0
 */
public class RetrofitFactory {

    private static RetrofitFactory instance=null;
    private final Retrofit retrofit;

    private RetrofitFactory(){
        retrofit = createRetrofit();
    }

    private static class Holder{
        private static RetrofitFactory INSTANCE=new RetrofitFactory();
    }

    public static RetrofitFactory getInstance(){
        return Holder.INSTANCE;
    }

    /**
     * 创建Retrofit实例
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:07
     */ 
    private Retrofit createRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.TENCENT_SERVER)
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    /**
     * 日志拦截器 Logger 用于设置网络请求日志显示
     * @param
     * @return
     * @author zhangyue
     * @time 2021/12/1 13:46
     */
    class MyLogger implements HttpLoggingInterceptor.Logger{

        @Override
        public void log(String message) {
            LogUtils.getInstance().d("$$NBA NET LOG$$",String.format("--> %s",message));
        }
    }

    /**
     * 创建OkHttp客户端
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:19
     */ 
    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.TIMEOUT,TimeUnit.SECONDS)
                .readTimeout(Constant.TIMEOUT,TimeUnit.SECONDS)
                .addInterceptor(createChangeBaseUrlInterceptor())
                .addInterceptor(createTokenInterceptor())
                .addInterceptor(createRetryInterceptor())
                .addInterceptor(createCookieInterceptor())
                .addInterceptor(createCommonParamsInterceptor())
                .build();
    }

    /**
     * 动态切换BaseUrl拦截器
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/1 14:31
     */ 
    private Interceptor createChangeBaseUrlInterceptor() {
        return new CustomInterceptor.ChangeBaseUrlInterceptor();
    }

    /**
     * 通用参数拦截器
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:24
     */ 
    private Interceptor createCommonParamsInterceptor() {
        return new CustomInterceptor.CommonParamsInterceptor();
    }

    /**
     * 创建Cookie拦截器
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:24
     */ 
    private Interceptor createCookieInterceptor() {
        return new CustomInterceptor.CookieInterceptor();
    }

    /**
     * 重试拦截器
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:23
     */ 
    private Interceptor createRetryInterceptor() {
        return new CustomInterceptor.RetryInterceptor();
    }

    /**
     * Token拦截器
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:22
     */ 
    private Interceptor createTokenInterceptor() {
        return new CustomInterceptor.TokenInterceptor();
    }

    /**
     * 创建服务实例
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:18
     */ 
    public <T> T create(Class<?> service){
        return (T) retrofit.create(service);
    }

}
