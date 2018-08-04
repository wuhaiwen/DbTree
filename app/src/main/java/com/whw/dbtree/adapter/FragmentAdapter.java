package com.whw.dbtree.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author wuhaiwen
 * @date 2018/8/1 0001
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
