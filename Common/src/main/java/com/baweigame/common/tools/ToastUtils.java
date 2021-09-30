package com.baweigame.common.tools;

import android.widget.Toast;

import com.baweigame.common.app.BaseApplication;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.zy.common.tools
 * @ClassName: ToastUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/29 9:44
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/29 9:44
 * @UpdateRemark:
 * @Version: 1.0
 */
public class ToastUtils {
    /**
     * 显示提示信息
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/29 9:46
     */
    public static void showToast(String msg){
        Toast.makeText(BaseApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
