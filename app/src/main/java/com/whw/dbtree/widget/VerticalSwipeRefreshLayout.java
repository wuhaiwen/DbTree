package com.whw.dbtree.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;

/**
 * @author wuhaiwen
 * @date 2018/8/7 0007
 */
public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout {

    private boolean viewPagerSlip;
    private float distance_x;
    private float distance_y;

    public VerticalSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                distance_x = ev.getX();
                distance_y = ev.getY();
                viewPagerSlip = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (viewPagerSlip)
                    return false;
                float endX = Math.abs(ev.getX() - distance_x);
                float endY = Math.abs(ev.getY() - distance_y);
                if (endX - endY > 0) {
                    viewPagerSlip = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
