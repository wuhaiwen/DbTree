package com.whw.dbtree.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author wuhaiwen
 * @date 2018/8/1 0001
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(),container,false);
        return view;
    }

    public abstract int getLayoutView();

    public abstract void initView();


}
