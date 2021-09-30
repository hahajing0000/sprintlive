package com.baweigame.sprintnba.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.baweigame.common.tools.BitmapUtils;
import com.baweigame.common.tools.DimenUtils;
import com.baweigame.common.tools.SPUtils;
import com.baweigame.sprintnba.R;
import com.baweigame.sprintnba.app.SprintNBAApp;
import com.baweigame.weiget.LoadingDialog;
import com.zy.common.utils.BitmapUtil;
import com.zy.mvvmcore.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class GuidePageActivity extends BaseActivity {
    private ViewPager vpGuideMain;
    private LinearLayout llGuideIndicator;
    private Button btnGuide3In;

    /**
     * 记录是否是最后一页
     */
    private boolean isLastPage = false;
    /**
     * 记录ViewPager滑动的状态
     */
    private boolean isDragPage = false;
    /**
     * 记录ViewPager滑动到最后一页时 执行方法的标志
     */
    private boolean canJumpPage = true;

    //指示器View集合
    List<View> indicators=new ArrayList<>();

    @Override
    protected Toolbar crateToolBar() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide_page;
    }

    @Override
    protected void initEvent() {
        btnGuide3In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuidePageActivity.this,SplashActivity.class));
                finish();
            }
        });

        vpGuideMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (isLastPage && isDragPage && positionOffsetPixels == 0){

                    if (canJumpPage){
                        canJumpPage = false;
                        jump2SplashActivity();
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                isLastPage = position == pagerList.size()-1;
                initIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                isDragPage = state == ViewPager.SCROLL_STATE_DRAGGING;
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initEnv() {
        boolean isInit = SPUtils.getInstance(SprintNBAApp.getAppContext(), null).getBoolean("isInit");
        if (isInit){
            startActivity(new Intent(GuidePageActivity.this,SplashActivity.class));
            overridePendingTransition(R.anim.left_in,R.anim.left_out);
            finish();
        }
        vpGuideMain = (ViewPager) findViewById(R.id.vp_guide_main);
        llGuideIndicator = (LinearLayout) findViewById(R.id.ll_guide_indicator);

        initViewPager();

        initIndicator(0);
    }

    @Override
    protected Dialog createLoadingDialog() {
        return new LoadingDialog(this);
    }

    @Override
    protected int getPrimaryDarkColor() {
        //提取图片第一个像素点作为状态栏颜色
        int pixColor = BitmapUtils.getPixColor(getResources(), R.mipmap.guide1);
//        int red = Color.red(pixColor);
//        int green = Color.green(pixColor);
//        int blue = Color.blue(pixColor);
        return pixColor;
    }

    /**
     * 跳转到启动页
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/28 17:07
     */
    private void jump2SplashActivity() {
        //存储SP标记
        SPUtils.getInstance(SprintNBAApp.getAppContext(),null).put("isInit",true);
        jumpToActivity(SplashActivity.class);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }

    /**
     * 初始化指示器
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/28 16:53
     */
    private void initIndicator(int index) {
        llGuideIndicator.removeAllViews();
        int size = pagerList.size();
        for (int i=0;i<size;i++){
            View view=new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) DimenUtils.dpToPx(30), (int) DimenUtils.dpToPx(30));
            params.setMargins((int)DimenUtils.dpToPx(20),0,0,0);
            view.setLayoutParams(params);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (i==index){

                    view.setBackground(getResources().getDrawable(R.drawable.indicator_select));

                }
                else{
                    view.setBackground(getResources().getDrawable(R.drawable.indicator_unselect));
                }
            }
            llGuideIndicator.addView(view);
        }
    }

    private List<View> pagerList=new ArrayList<>(3);
    /**
     * 初始化ViewPager
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/28 16:53
     */
    private void initViewPager() {
        View view1=View.inflate(this,R.layout.guide1_layout,null);
        View view2=View.inflate(this,R.layout.guide2_layout,null);
        View view3=View.inflate(this,R.layout.guide3_layout,null);
        btnGuide3In = (Button) view3.findViewById(R.id.btn_guide3_in);
        pagerList.add(view1);
        pagerList.add(view2);
        pagerList.add(view3);

        vpGuideMain.setAdapter(new GuidePagerAdapter(this,pagerList));
    }

    /**
     * ViewPager适配器
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/28 16:53
     */
    private class GuidePagerAdapter extends PagerAdapter {
        private Context mContext;
        private List<View> mDataSource;
        public GuidePagerAdapter(Context context,List<View> dataSource){
            mContext=context;
            mDataSource=dataSource;
        }

        @Override
        public int getCount() {
            return mDataSource.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mDataSource.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(mDataSource.get(position));
        }
    }
}