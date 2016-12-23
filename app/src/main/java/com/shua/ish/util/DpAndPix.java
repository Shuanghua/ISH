package com.shua.ish.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by SHUA on 2016/6/24.
 */
public class DpAndPix {

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
