package com.shua.ish.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shua.ish.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Tab
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    @InjectView(R.id.video_tabLayout)
    TabLayout mTabLayout;
    @InjectView(R.id.video_viewPager)
    ViewPager mViewPager;

    List<Fragment> mFragments;

    public VideoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.inject(this, view);
        addFragment();
        return view;
    }

    private void addFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new VideosFragment());
        mFragments.add(new LiveFragment());
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.video);
                case 1:
                    return getString(R.string.live);
                default:
                    return "";
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
