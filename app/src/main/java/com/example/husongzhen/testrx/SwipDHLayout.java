package com.example.husongzhen.testrx;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by husongzhen on 17/10/31.
 */

public class SwipDHLayout extends LinearLayout {


    private static final float AUTO_FINISHED_SPEED_LIMIT = 2000;
    private ViewDragHelper mDragger;
    private Point autoBackPoint = new Point();
    private View mDragView;
    private int verticalDragRange;
    private float finishAnchor;

    public SwipDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mDragView;
            }

//
//            @Override
//            public int getViewHorizontalDragRange(View child) {
//                return getMeasuredWidth() - mDragView.getMeasuredWidth();
//            }
//
//
//            @Override
//            public int getViewVerticalDragRange(View child) {
//                return getMeasuredHeight() - mDragView.getMeasuredHeight();
//            }

            /**
             *
             *
             *
             * 只返回左边控件的位置， 所以，
             *
             * @param child
             * @param left
             * @param dx
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                int leftBound = getPaddingLeft();
//                int leftMaxBound = getWidth() - child.getWidth() - leftBound;
//                int newLeft = Math.min(Math.max(left, leftBound), leftMaxBound);
                return 0;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
//                可拖动的最大范围
                final int topBound = -verticalDragRange;
                final int bottomBound = getPaddingTop();
                int result = Math.min(Math.max(top, topBound), bottomBound);
                return result;
            }


            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == mDragView) {
                    int distance = releasedChild.getTop() - autoBackPoint.y;
                    if (isBackSpeed(xvel, yvel)
                            || Math.abs(distance) >= finishAnchor) {
                        mDragger.settleCapturedViewAt(0, -getHeight());
                    } else {
                        mDragger.settleCapturedViewAt(0, autoBackPoint.y);
                    }
                    invalidate();
                }
            }


            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);
                mDragger.captureChildView(mDragView, pointerId);
            }
        });
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    private boolean isBackSpeed(float xvel, float yvel) {
        return Math.abs(yvel) > Math.abs(xvel) && Math.abs(yvel) > AUTO_FINISHED_SPEED_LIMIT;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        verticalDragRange = h;
        finishAnchor = verticalDragRange * 0.5f;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
    }


    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        autoBackPoint.x = mDragView.getLeft();
        autoBackPoint.y = mDragView.getTop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }


}
