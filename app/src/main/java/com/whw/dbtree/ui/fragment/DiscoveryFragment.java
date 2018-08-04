package com.whw.dbtree.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whw.dbtree.R;
import com.whw.dbtree.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DiscoveryFragment extends BaseFragment {


    @BindView(R.id.discovery_viewpager)
    ViewPager discovery_viewPager;

    @BindView(R.id.discovery_tab_layout)
    TabLayout discovery_tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayoutView(),container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_discovery;
    }

    @Override
    public void initView() {
        TabAdapter tabAdapter = new TabAdapter(getActivity().getSupportFragmentManager());
        discovery_viewPager.setAdapter(tabAdapter);
        discovery_tabLayout.setupWithViewPager(discovery_viewPager);
    }

    class TabAdapter extends FragmentPagerAdapter{


        List<BaseFragment> fragmentList = new ArrayList<>();
        String[] title = {"拦截记录","拦截规则","关于"};

        public TabAdapter(FragmentManager fm) {
            super(fm);
            fragmentList.add(new HomePageFragment());
            fragmentList.add(new HomePageFragment());
            fragmentList.add(new HomePageFragment());
//            fragmentList.add(new RulesFragment());
//            fragmentList.add(new AboutFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
