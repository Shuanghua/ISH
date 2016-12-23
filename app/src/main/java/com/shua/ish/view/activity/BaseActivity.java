package com.shua.ish.view.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;

import com.shua.ish.R;

/**
 * BaseActivity
 * Created by SHUA on 2016/5/30.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected AppBarLayout mAppBar;
    protected boolean mIsHidden = false;

    public SharedPreferences mConfigPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResID());
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null || mAppBar == null) {
            throw new IllegalStateException(
                    "The subclass of ToolbarActivity must contain a toolbar.");
        }
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mConfigPreferences = getSharedPreferences("config", MODE_PRIVATE);
    }

    /**
     * 设置布局
     */
    public abstract
    @LayoutRes
    int setLayoutResID();

    protected void hideOrShowToolbar() {
        mAppBar.animate()
                .translationY(mIsHidden ? 0 : -mAppBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }

}
