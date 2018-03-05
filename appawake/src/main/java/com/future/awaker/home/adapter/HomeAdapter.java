package com.future.awaker.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.future.awaker.home.fragment.HomeListFragment;
import com.future.awaker.news.fragment.NewListFragment;
import com.future.awaker.video.fragment.VideoListFragment;

import java.util.List;

/**
 * Copyright ©2017 by ruzhan
 */

public class HomeAdapter extends FragmentPagerAdapter {

    public static final int HOME = 0;
    public static final int NEW = 1;
    public static final int VIDEO = 2;

    private List<String> titles;
    private HomeListFragment homeListFragment;
    private NewListFragment newListFragment;
    private VideoListFragment videoListFragment;

    public HomeAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
    }

    public void setCat(int cat) {
        if (videoListFragment != null) {
            videoListFragment.setCat(cat);
        }
    }

    public Fragment getCurrentFrag(int position) {
        Fragment fragment = null;
        switch (position) {
            case HOME:
                fragment = homeListFragment;
                break;
            case NEW:
                fragment = newListFragment;
                break;
            case VIDEO:
                fragment = videoListFragment;
                break;
        }
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case HOME:
                homeListFragment = HomeListFragment.newInstance();
                fragment = homeListFragment;
                break;
            case NEW:
                newListFragment = NewListFragment.newInstance(0);
                fragment = newListFragment;
                break;
            case VIDEO:
                videoListFragment = VideoListFragment.newInstance();
                fragment = videoListFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
