package com.shua.ish.view.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * 状态栏颜色
 */

public class StatusBarCompat {
    private static final int INVALID_VAL = -1;
    private static final int COLOR_DEFAULT = Color.parseColor("#20000000");

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void compat(Activity activity, int statusColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0+ 直接设置颜色
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusColor);
            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);

            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }
        }

        /**
         * 4.4 填充View
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            int statusBarHeight = getStatusBarHeight(activity);
            View mChildView = mContentView.getChildAt(0);

            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                    lp.topMargin += statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
            }

            View statusBarView = mContentView.getChildAt(0);

            if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
                statusBarView.setBackgroundColor(statusColor);
                return;
            }

            statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            statusBarView.setBackgroundColor(statusColor);
            mContentView.addView(statusBarView, 0, lp);
        }
    }

    /**
     * 不设置状态栏颜色
     */
    public static void compat(Activity activity) {
        compat(activity, INVALID_VAL);
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }
}
