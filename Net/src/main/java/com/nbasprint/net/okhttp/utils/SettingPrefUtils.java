package com.nbasprint.net.okhttp.utils;

import android.text.TextUtils;

import com.baweigame.common.app.BaseApplication;
import com.baweigame.common.tools.SPUtils;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.nbasprint.net.okhttp.utils
 * @ClassName: SettingPrefUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/12/1 13:32
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/12/1 13:32
 * @UpdateRemark:
 * @Version: 1.0
 */
public class SettingPrefUtils {
    private static String SPNAME="NBA_NET_SP";
    public static String getUid() {

        return SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).getString("uid","");
    }

    public static void saveUid(String uid) {
        SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).put("uid",uid);
    }

    public static String getToken() {
        return SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).getString("token");
    }

    public static void saveToken(String token) {
        SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).put("token",token);
    }

    public static String getCookies() {
        return SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).getString("cookies");
    }

    public static void saveCookies(String cookies) {
        SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).put("cookies",cookies);
    }

    public static String getUsername() {
        return SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).getString("username");
    }

    public static void saveUsername(String username) {
        SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).put("username",username);
    }

    public static String getPassword() {
        return SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).getString("password");
    }

    public static void savePassword(String password) {
        SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).put("password",password);
    }

    public static String getNickname() {
       return SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).getString("nickname");
    }

    public static void saveNickname(String nickname) {
        SPUtils.getInstance(BaseApplication.getAppContext(),SPNAME).put("nickname",nickname);
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(getCookies()) && !TextUtils.isEmpty(getToken());
    }

    public static void logout() {
        saveCookies("");
        saveNickname("");
        saveToken("");
        saveUid("");
        saveUsername("");
    }
}
