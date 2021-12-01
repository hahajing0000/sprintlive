package com.baweigame.weiget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import com.zy.common.utils.DisplayUtils;

import androidx.annotation.Nullable;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.mvvm.wiget
 * @ClassName: NewProgressView
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/18 10:16
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/18 10:16
 * @UpdateRemark:
 * @Version: 1.0
 */
public class NewProgressView extends View {
    private Paint mPaint;
    private Paint txtPint;

    /**
     * 圆形颜色
     */
    private int mColor=Color.YELLOW;
    private int strokeWidth=DisplayUtils.dip2px(getContext(),3);
    private int txtColor=Color.WHITE;
    private int txtSize= DisplayUtils.sp2px(getContext(),20);

    private float sweepAngle=360;

    int defaultWidth=200;
    int defaultHeight=200;

    private String content="5";



    int padding=10;
    private int centerX;
    private int centerY;

    private NewProgressVeiwListener listener=null;

    public void setListener(NewProgressVeiwListener listener) {
        this.listener = listener;
    }

    public NewProgressView(Context context) {
        super(context);
        initPaint();
    }

    public NewProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        doTypedArray(context,attrs);

        initPaint();
    }

    public NewProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * widthMeasureSpec heightMeasureSpec
     * 32位整型   前2位代表Mode 后30位是Size
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,defaultHeight);
        }
        else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,defaultHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = getMeasuredWidth()/2;
        centerY = getMeasuredHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取内间距
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        RectF rectF=new RectF(0+padding,0+padding,getMeasuredWidth()-padding,getMeasuredHeight()-padding);
        canvas.drawArc(rectF,0,sweepAngle,false,mPaint);

        Rect bounds=new Rect();
        String text=String.valueOf(content)+"S";
        txtPint.getTextBounds(text,0,text.length(),bounds);
        float offSet=(bounds.top+bounds.bottom)/2;
        canvas.drawText(text,centerX,centerY-offSet,txtPint);

//        Paint.FontMetrics fontMetrics=new Paint.FontMetrics();
//        mPaint.getFontMetrics(fontMetrics);
//        float offset=(fontMetrics.descent+fontMetrics.ascent)/2;
//        canvas.drawText("100",centerX,centerY-offset*2,txtPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startDoAnimator();
    }

    /**
     * 初始化画笔
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/11/18 10:19
     */ 
    private void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        txtPint=new Paint();
        txtPint.setColor(txtColor);
        txtPint.setTextSize(txtSize);
        txtPint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 处理自定义属性
     * @param
     * @return
     * @author zhangyue
     * @time 2021/11/18 10:49
     */
    private void doTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NewProgressView);
        mColor=typedArray.getColor(R.styleable.NewProgressView_strokeColor,mColor);
        strokeWidth=typedArray.getInteger(R.styleable.NewProgressView_strokeW,strokeWidth);
        txtColor=typedArray.getColor(R.styleable.NewProgressView_txtColor,txtColor);
        txtSize=typedArray.getInteger(R.styleable.NewProgressView_txtSize,txtSize);
        typedArray.recycle();
    }

    private void startDoAnimator(){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(5*1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                sweepAngle=360-animatedValue*360;
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                listener.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public interface NewProgressVeiwListener{
        void finish();
    }
}
