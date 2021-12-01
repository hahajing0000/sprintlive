package com.baweigame.sprintnba.ui;

import android.app.Dialog;
import android.widget.ImageView;

import com.baweigame.common.tools.BitmapUtils;
import com.baweigame.sprintnba.R;
import com.baweigame.weiget.LoadingDialog;
import com.baweigame.weiget.NewProgressView;
import com.zy.mvvmcore.view.BaseActivity;

import androidx.appcompat.widget.Toolbar;

public class SplashActivity extends BaseActivity {
    private ImageView ivBg;
    private NewProgressView npvSplash;

    int[] imgs = new int[]{
            R.mipmap.irving,
            R.mipmap.bryant,
            R.mipmap.james,
            R.mipmap.harden,
            R.mipmap.curry};


    @Override
    protected Toolbar crateToolBar() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEvent() {
        npvSplash.setListener(new NewProgressView.NewProgressVeiwListener() {
            @Override
            public void finish() {
                jumpToActivity(HomeActivity.class);
            }
        });

    }

    @Override
    protected int getPrimaryDarkColor() {
        //提取图片第一个像素点作为状态栏颜色
        int pixColor = BitmapUtils.getPixColor(getResources(), R.mipmap.james);
        return pixColor;
    }

    @Override
    protected void loadData() {
        int index = (int) (Math.random() * imgs.length);

        ivBg.setImageResource(imgs[index]);

    }

    @Override
    protected void initEnv() {
        ivBg = (ImageView) findViewById(R.id.ivBg);
        npvSplash = (NewProgressView) findViewById(R.id.npv_splash);
    }

    @Override
    protected Dialog createLoadingDialog() {
        return new LoadingDialog(this);
    }
}