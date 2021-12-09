package com.nbasprint.net.okhttp.interceptor;

import android.text.TextUtils;

import com.baweigame.common.tools.LogUtils;
import com.nbasprint.net.BuildConfig;
import com.nbasprint.net.Constant;
import com.nbasprint.net.okhttp.utils.SettingPrefUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.nbasprint.net.okhttp.interceptor
 * @ClassName: CustomInterceptor
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/1 13:26
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/1 13:26
 * @UpdateRemark:
 * @Version: 1.0
 */
public class CustomInterceptor {
    /**
     * 动态更换BaseUrl拦截器
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/12/1 14:20
     */ 
    public static class ChangeBaseUrlInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRrequest = chain.request();
            HttpUrl oldUrl = oldRrequest.url();
            Request.Builder builder = oldRrequest.newBuilder();
            List<String> headers = oldRrequest.headers(Constant.BASEURL_KEY);

            HttpUrl newUrl=null;
            if (headers!=null&&headers.size()>0&&!TextUtils.isEmpty(headers.get(0).trim())){
                String urlName=headers.get(0).trim();
                builder.removeHeader(Constant.BASEURL_KEY);


                if (Constant.BASEURL_SERVER_HUPUFORUM.equals(urlName)){
                    newUrl=HttpUrl.parse(BuildConfig.HUPU_FORUM_SERVER);
                }else if (Constant.BASEURL_SERVER_HUPU_GAMES.equals(urlName)){
                    newUrl=HttpUrl.parse(BuildConfig.HUPU_GAMES_SERVER);
                }else if (Constant.BASEURL_SERVER_HUPU_LOGIN.equals(urlName)){
                    newUrl=HttpUrl.parse(BuildConfig.HUPU_LOGIN_SERVER);
                }else if (Constant.BASEURL_SERVER_TECENT_URL_SERVER.equals(urlName)){
                    newUrl=HttpUrl.parse(BuildConfig.TECENT_URL_SERVER);
                }else if (Constant.BASEURL_SERVER_TECENT_URL_SERVER_1.equals(urlName)){
                    newUrl=HttpUrl.parse(BuildConfig.TECENT_URL_SERVER_1);
                }else if (Constant.BASEURL_SERVER_TECENT.equals(urlName)){
                    newUrl=HttpUrl.parse(BuildConfig.TENCENT_SERVER);
                }else  if (Constant.BASEURL_SERVER_TMIAAO.equals(urlName)){
                    newUrl=HttpUrl.parse(BuildConfig.TMIAAO_SERVER);
                }
                HttpUrl finalUrl = oldUrl.newBuilder()
                        .scheme(newUrl.scheme())
                        .host(newUrl.host())
                        .port(newUrl.port())
                        .build();
                Request newRequest = builder.url(finalUrl).build();
                return chain.proceed(newRequest);
            }
            else{
                return chain.proceed(oldRrequest);
            }

        }
    }

    /**
     * Token拦截器 处理Token
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:43
     */ 
    public static class TokenInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request());
        }
    }

    /**
     * 重试拦截器 处理失败后的重试逻辑
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:44
     */ 
    public static class RetryInterceptor implements Interceptor{

        /**
         * 最大重试次数
         */
        int maxRetryCount=5;
        /**
         * 当前重试次数
         */
        int retryNum=0;
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetryCount) {
                retryNum++;
                response = chain.proceed(request);
            }
            return response;
        }
    }

    /**
     * Cookie拦截器 处理Cookie
     * @param 
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:44
     */ 
    public static class CookieInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            if (!TextUtils.isEmpty(SettingPrefUtils.getCookies())
                    && !original.url().toString().contains("loginUsernameEmail")) {
                Request request = original.newBuilder()
                        .addHeader("Cookie", "u=" + SettingPrefUtils.getCookies() + ";") // 不能转UTF-8
                        .build();
                LogUtils.getInstance().d("CookieInterceptor","okhttplog: set header cookie:" + SettingPrefUtils.getCookies());
                LogUtils.getInstance().d("CookieInterceptor","okhttplog: set header cookie:" + URLEncoder.encode(SettingPrefUtils.getCookies()));
                return chain.proceed(request);
            } else {
                for (String header : chain.proceed(original).headers("Set-Cookie")) {
                    if (header.startsWith("u=")) {
                        String cookie = header.split(";")[0].substring(2);
                        LogUtils.getInstance().d("CookieInterceptor","okhttplog: add cookie:" + cookie);
                        if (!TextUtils.isEmpty(cookie)) {
                            SettingPrefUtils.saveCookies(cookie);
                        }
                    }
                }
            }
            return chain.proceed(original);
        }
    }

    /**
     * 通用参数拦截器 用于对请求添加通用请求参数
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/12/1 13:44
     */ 
    public static class CommonParamsInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();

            HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter("appver", "1.0.2.2")
                    .addQueryParameter("appvid", "1.0.2.2")
                    .addQueryParameter("network", "wifi");

            Request newRequest = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .build();

            return chain.proceed(newRequest);
        }
    }
}
