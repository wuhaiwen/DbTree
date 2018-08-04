package com.whw.dbtree.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.whw.dbtree.R;
import com.whw.dbtree.adapter.MainRecycleViewAdapter;
import com.whw.dbtree.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageFragment extends BaseFragment {

    @BindView(R.id.home_page_recyclerview)
    protected RecyclerView home_page_recycleView;

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static HomePageFragment newInstance(int fragmentIndex) {

        HomePageFragment fragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("fragmentIndex", fragmentIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(),container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_home_page;
    }

    @Override
    public void initView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("" + i);
        }
        home_page_recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        MainRecycleViewAdapter mainRecycleViewAdapter = new MainRecycleViewAdapter(getContext(), list);
        home_page_recycleView.setAdapter(mainRecycleViewAdapter);
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
