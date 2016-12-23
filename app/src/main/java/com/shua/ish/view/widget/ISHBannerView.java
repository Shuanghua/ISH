package com.shua.ish.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jude.rollviewpager.RollPagerView;

/**
 * Created by SHUA on 2016/6/24.
 */
public class ISHBannerView extends RollPagerView {
    public ISHBannerView(Context context) {
        super(context);
    }

    public ISHBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ISHBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
