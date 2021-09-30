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

import androidx.annotation.Nullable;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.baweigame.weiget
 * @ClassName: CountDownView
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/29 11:07
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/29 11:07
 * @UpdateRemark:
 * @Version: 1.0
 */
public class CountDownView extends View {
    //画笔
    private Paint paint;
    private Paint txtPaint;

    //默认宽高
    private int defaultW=200;
    private int defaultH=200;
    //扇形margin
    private int margin=20;

    //默认颜色
    private int defaultColor= Color.YELLOW;
    //默认文本颜色
    private int defaultTxtColor=Color.WHITE;
    //默认文本大小
    private int defaultTxtSize=50;
    private String text="%s秒";
    //扇形区域
    private RectF rect;

    //扇形绘制角度
    private float angle=0;
    //状态监听
    private StateListener stateListener;

    //中心点XY坐标
    private int centerX=0;
    private int centerY=0;


    public CountDownView(Context context) {
        super(context);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        defaultColor= typedArray.getColor(R.styleable.CountDownView_color,defaultColor);
        defaultTxtColor=typedArray.getColor(R.styleable.CountDownView_textColor,defaultTxtColor);
        defaultTxtSize=typedArray.getInteger(R.styleable.CountDownView_textSize,defaultTxtSize);
        typedArray.recycle();
        initPaint();
    }


    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultW,defaultH);
        }
        else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultW,widthSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(heightSize,defaultH);
        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //设置扇形区域
        rect = new RectF(0+margin,0+margin,w-margin,h-margin);
        centerX= (int) ((rect.right-rect.left)/2);
        centerY= (int) ((rect.bottom-rect.top)/2);
    }

    //初始化画笔
    private void initPaint() {
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(defaultColor);

        txtPaint=new Paint();
        txtPaint.setStyle(Paint.Style.FILL);
        txtPaint.setColor(defaultTxtColor);
        txtPaint.setTextSize(defaultTxtSize);
        txtPaint.setTextAlign(Paint.Align.CENTER);

    }

    //设置状态监听
    public void setCallback(StateListener callback) {
        this.stateListener = callback;
    }

    private String mSeconds="";

    /**
     * 开始倒计时
     * @param seconds
     */
    public void start(final int seconds){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(seconds*1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();

                angle= 360-animatedValue*360;
                mSeconds=String.valueOf((int) angle/72);
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
                if (stateListener!=null){
                    stateListener.begin();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (stateListener!=null){
                    stateListener.end();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(rect,
                360,
                angle,
                false,
                paint);
        String txtSeconds = String.format(text, mSeconds);
        Rect bounds=new Rect();
        txtPaint.getTextBounds(txtSeconds,0,txtSeconds.length(),bounds);
        float offSet=(bounds.top+bounds.bottom)/2;

        canvas.drawText(txtSeconds,centerX+txtPaint.measureText(txtSeconds)/2,centerY+(bounds.bottom-bounds.top)/2-offSet,txtPaint);
    }

    /**
     * 状态监听
     * @param
     * @return
     * @author zhangyue
     * @time 2021/9/28 14:13
     */
    public interface StateListener{
        //开始
        void begin();
        //结束
        void end();
    }
}
