package com.hencode.kagurasansan.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @auther kagurasansan
 * @time 2019/4/8.9:33 AM
 * @des ${TODO}
 */
public class Utils{
    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, Resources.getSystem().getDisplayMetrics());
    }
}
