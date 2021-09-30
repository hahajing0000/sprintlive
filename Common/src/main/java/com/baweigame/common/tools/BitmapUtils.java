package com.baweigame.common.tools;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

/**
 * @ProjectName: SprintNBAMVVM
 * @Package: com.baweigame.common.tools
 * @ClassName: BitmapUtils
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/9/30 8:42
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/9/30 8:42
 * @UpdateRemark:
 * @Version: 1.0
 */
public class BitmapUtils {
    /**
     * 获取图片第一个点arbg
     * @param
     * @return 
     * @author zhangyue
     * @time 2021/9/30 8:45
     */ 
    public static int getPixColor(Resources resources,int id){
        Bitmap src =  BitmapFactory.decodeResource(resources,id);
        int A, R, G, B;
        int pixelColor;
        int height = src.getHeight();
        int width = src.getWidth();


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = src.getPixel(x, y);
//                A = Color.alpha(pixelColor);
//                R = Color.red(pixelColor);
//                G = Color.green(pixelColor);
//                B = Color.blue(pixelColor);
                return pixelColor;
            }
        }
        return 0;
    }
}
