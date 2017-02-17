package io.laterain.inobugs2.util;

import android.content.Context;

/**
 * Created by dengyuchi on 2/16/17.
 */

public class ScreenDensityUtil {

    /**
     * Convert from dp to px based on screen density.
     *
     * @param context the given context of the environment in which the caller of this method is
     * @param dpValue the given value in dp
     * @return the converted value in px
     */
    public static int dp2Px(Context context, double dpValue) {
        final double scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Convert from px to dp based on screen density.
     *
     * @param context the given context of the environment in which the caller of this method is
     * @param pxValue the given value in px
     * @return the converted value in dp
     */
    public static int px2Dp(Context context, double pxValue) {
        final double scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
