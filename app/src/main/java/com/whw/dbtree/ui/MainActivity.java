package com.whw.dbtree.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.whw.dbtree.R;
import com.whw.dbtree.ui.base.BaseActivity;
import com.whw.dbtree.ui.base.BaseFragment;
import com.whw.dbtree.ui.fragment.DiscoveryFragment;
import com.whw.dbtree.ui.fragment.HomePageFragment;
import com.whw.dbtree.ui.fragment.MessageFragment;
import com.whw.dbtree.ui.fragment.MineFragment;
import com.whw.dbtree.widget.nav.BottomNavigationLayout;
import com.whw.dbtree.widget.nav.NavigationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    protected ViewPager viewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigationLayout bottomNavigationView;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    int[] toolBar_name = {R.string.home_page, R.string.message, R.string.discovery, R.string.mine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    public static int fetchContextColor(Context context, int androidAttribute) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{androidAttribute});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }
    @Override
    protected void initView() {

        toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp);
        int color = fetchContextColor(this, R.attr.colorPrimary);
        bottomNavigationView.addItem(new NavigationItem(getResources().getString(R.string.home_page), getResources().getDrawable(R.drawable.ic_home_black_24dp)).setActiveColor(color))
                .addItem(new NavigationItem(getResources().getString(R.string.message), getResources().getDrawable(R.drawable.ic_mail_black_24dp)).setActiveColor(color))
                .addItem(new NavigationItem(getResources().getString(R.string.discovery), getResources().getDrawable(R.drawable.ic_search_black_24dp)).setActiveColor(color))
                .addItem(new NavigationItem(getResources().getString(R.string.mine), getResources().getDrawable(R.drawable.ic_person_black_24dp)).setActiveColor(color))
                .initialise();

        TextView tvStatusBadge = bottomNavigationView.getTab(0).getTvBadge();
        tvStatusBadge.setVisibility(View.VISIBLE);
        tvStatusBadge.setText("10");

        bottomNavigationView.setTabSelectedListener(new BottomNavigationLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                toolbar.setTitle(getResources().getString(toolBar_name[position]));
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                bottomNavigationView.getTab(position).select();
                toolbar.setTitle(getResources().getString(toolBar_name[position]));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        List<BaseFragment> list = new ArrayList<>();
        list.add(new HomePageFragment());
        list.add(new MessageFragment());
        list.add(new DiscoveryFragment());
        list.add(new MineFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);

        ViewCompat.setOnApplyWindowInsetsListener(viewPager,
                new OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v,
                                                                  WindowInsetsCompat insets) {
                        insets = ViewCompat.onApplyWindowInsets(v, insets);
                        if (insets.isConsumed()) {
                            return insets;
                        }

                        boolean consumed = false;
                        for (int i = 0, count = viewPager.getChildCount(); i < count; i++) {
                            ViewCompat.dispatchApplyWindowInsets(viewPager.getChildAt(i), insets);
                            if (insets.isConsumed()) {
                                consumed = true;
                            }
                        }
                        return consumed ? insets.consumeSystemWindowInsets() : insets;
                    }
                });
        viewPager.setAdapter(viewPagerAdapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        List<BaseFragment> fragmentList;

        public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
