package com.whw.dbtree.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    protected ViewPager viewPager;

    @BindView(R.id.nav_bottom)
    BottomNavigationLayout bottomNavigationView;

    @BindView(R.id.tbar_main_page)
    Toolbar toolbar;


    int[] toolBar_name = {R.string.home_page, R.string.message, R.string.discovery, R.string.mine};

    private List<BaseFragment> fragmentList;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    public static int getColor(Context context, int color) {
        return context.getResources().getColor(color);
    }


    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();

        GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
//                swipeRefreshLayout_main.setRefreshing(true);
                RecyclerView recyclerView = fragmentList.get(viewPager.getCurrentItem()).getView().findViewById(R.id.home_page_recyclerview);
                recyclerView.smoothScrollToPosition(0);
                fragmentList.get(viewPager.getCurrentItem()).refreshUi();
                return super.onDoubleTap(e);
            }
        });

        setSupportActionBar(toolbar);
        toolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        initBottomNavigation();
        initViewPager();
        for (int i = 0; i < 3; i++)
            bottomNavigationView.getTab(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return gestureDetector.onTouchEvent(event);
                }
            });
    }


    private void initBottomNavigation() {
        int color = getColor(this, R.color.main_theme_color);
        bottomNavigationView.addItem(new NavigationItem(getResources().getString(R.string.home_page), getResources().getDrawable(R.drawable.ic_home_black_24dp)).setActiveColor(color))
                .addItem(new NavigationItem(getResources().getString(R.string.message), getResources().getDrawable(R.drawable.ic_mail_black_24dp)).setActiveColor(color))
                .addItem(new NavigationItem(getResources().getString(R.string.discovery), getResources().getDrawable(R.drawable.ic_search_black_24dp)).setActiveColor(color))
                .addItem(new NavigationItem(getResources().getString(R.string.mine), getResources().getDrawable(R.drawable.ic_person_black_24dp)).setActiveColor(color))
                .initialise();

        bottomNavigationView.setTabSelectedListener(new BottomNavigationLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                toolbar.setTitle(getResources().getString(toolBar_name[position]));
                viewPager.setCurrentItem(position);
                invalidateOptionsMenu();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }

    private void initViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                bottomNavigationView.selectTab(position);
            }

            @Override
            public void onPageSelected(int position) {
//                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                bottomNavigationView.selectTab(position);
                toolbar.setTitle(getResources().getString(toolBar_name[position]));
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        fragmentList = new ArrayList<>();
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new DiscoveryFragment());
        fragmentList.add(new MineFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try{

        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        getMenuInflater().inflate(R.menu.toobar_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (viewPager.getCurrentItem()) {
            case 0:
                menu.findItem(R.id.item_search).setVisible(true);
                menu.findItem(R.id.item_sort).setVisible(true);
                menu.findItem(R.id.item_settings).setVisible(false);
                break;
            case 1:
                menu.findItem(R.id.item_search).setVisible(false);
                menu.findItem(R.id.item_sort).setVisible(false);
                menu.findItem(R.id.item_settings).setVisible(false);
                break;
            case 2:
                menu.findItem(R.id.item_search).setVisible(true);
                menu.findItem(R.id.item_sort).setVisible(false);
                menu.findItem(R.id.item_settings).setVisible(false);
                break;
            case 3:
                menu.findItem(R.id.item_search).setVisible(false);
                menu.findItem(R.id.item_sort).setVisible(false);
                menu.findItem(R.id.item_settings).setVisible(true);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.item_sort:
                break;
            case R.id.item_settings:
                initAppTheme();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initAppTheme() {
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

}
