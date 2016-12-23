package com.shua.ish;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.shua.ish.view.activity.BaseActivity;
import com.shua.ish.view.fragment.ImageFragment;
import com.shua.ish.view.fragment.MeFragment;
import com.shua.ish.view.fragment.NewsFragment;
import com.shua.ish.view.fragment.VideoFragment;
import com.shua.ish.view.widget.StatusBarCompat;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

@SuppressWarnings("deprecation")
public class MainActivity extends BaseActivity {

    @InjectView(R.id.bottom_tab)
    PagerBottomTabLayout mBottomTab;
    private Fragment fs;

    private ArrayList<Fragment> mFragments;
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        initBottomTab();
        initFragment();
    }

    @Override
    public int setLayoutResID() {
        return R.layout.activity_main;
    }

    /**
     * 初始化 Fragment
     */
    @SuppressLint("CommitTransaction")
    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new NewsFragment());
        mFragments.add(new ImageFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new MeFragment());
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fs = mFragments.get(0);
        mTransaction.add(R.id.container_fragment, fs).commit();
    }

    /**
     * 初始化底部 Tab
     */
    private void initBottomTab() {
        TabItemBuilder tabItemBuild = new TabItemBuilder(this).create()
                .setDefaultIcon(android.R.drawable.ic_menu_send)
                .setText(getString(R.string.news_fragment))
                .setSelectedColor(getResources().getColor(R.color.colorBlue))
                .setTag("A")
                .build();

        //构建导航栏,得到 Controller 进行后续控制
        Controller controller = mBottomTab.builder()
                .addTabItem(tabItemBuild)
                .addTabItem(android.R.drawable.ic_menu_compass, getString(R.string.image_fragment),
                        getResources().getColor(R.color.colorRed))
                .addTabItem(android.R.drawable.ic_menu_search, getString(R.string.video_fragment),
                        getResources().getColor(R.color.colorYellow))
                .addTabItem(android.R.drawable.ic_menu_help, getString(R.string.me_fragment),
                        getResources().getColor(R.color.colorGreen))
//                .setMode(TabLayoutMode.HIDE_TEXT)
//                .setMode(TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .setMode(TabLayoutMode.HIDE_TEXT |
                        TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();
        controller.addTabItemClickListener(listener);
    }

    /**
     * 底部 Tab 监听
     */
    private OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @SuppressLint("CommitTransaction")
        @Override
        public void onSelected(int index, Object tag) {
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentIsAdded(fs, mFragments.get(index));
            setToolbarTheme(index);
        }

        @Override
        public void onRepeatClick(int index, Object tag) {
            //二次点击（可做刷新操作）
        }
    };

    /**
     * 防止切换时，Fragment 重复实例化
     *
     * @param from 将要隐藏的 Fragment
     * @param to   将要显示的 Fragment
     */
    private void fragmentIsAdded(Fragment from, Fragment to) {
        if (!to.isAdded()) {
            mTransaction.hide(from).add(R.id.container_fragment, to).commit();
        } else {
            mTransaction.hide(from).show(to).commit();
        }

        fs = to;//保存当前的 Fragment 实例
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fs = null;
        mFragments = null;
        mTransaction = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private void setToolbarTheme(int index) {
        switch (index) {
            case 0:
                mToolbar.setBackgroundResource(R.color.colorBlue);
                mToolbar.setTitle(getString(R.string.news_fragment));
                StatusBarCompat.compat(this, getResources()
                        .getColor(R.color.colorBlue));
                break;
            case 1:
                mToolbar.setBackgroundResource(R.color.colorRed);
                mToolbar.setTitle(getString(R.string.image_fragment));
                StatusBarCompat.compat(this, getResources()
                        .getColor(R.color.colorRed));
                break;
            case 2:
                mToolbar.setBackgroundResource(R.color.colorYellow);
                mToolbar.setTitle(getString(R.string.video_fragment));
                StatusBarCompat.compat(this, getResources()
                        .getColor(R.color.colorYellow));
                break;
            case 3:
                mToolbar.setBackgroundResource(R.color.colorGreen);
                mToolbar.setTitle(getString(R.string.me_fragment));
                StatusBarCompat.compat(this, getResources()
                        .getColor(R.color.colorGreen));
                break;
        }
    }
}
