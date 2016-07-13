package com.zhan.directioncontrolview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hrz on 2016/7/3.
 */
public class DirectionControlView extends View implements
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String TAG = DirectionControlView.class.getSimpleName();
    private static final int MIN_OFFSET_VALUE = 20;
    private GestureDetector mGestureDetector;
    private DirectionControlListener mDirectionControlListener;

    public DirectionControlView(Context context) {
        super(context);
        init();
    }

    public DirectionControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DirectionControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(this);
        mGestureDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.i(TAG, "onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.i(TAG, "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.i(TAG, "onSingleTapUp");

        if (mDirectionControlListener != null) {//单击事件
            mDirectionControlListener.singleClick();
        }
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.i(TAG, "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.i(TAG, "onLongPress");

        if (mDirectionControlListener != null) {//长按点击事件
            mDirectionControlListener.longClick();
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i(TAG, "onFling");

        float offsetX = e1.getX() - e2.getX();//X方向偏移量
        float offsetY = e1.getY() - e2.getY();//Y方向偏移量

        if (Math.abs(offsetX) > Math.abs(offsetY)) {//左滑或者右滑
            if (e1.getX() - e2.getX() > MIN_OFFSET_VALUE) {
                if (mDirectionControlListener != null) {//左滑
                    mDirectionControlListener.leftSlide();
                }
            } else {
                if (mDirectionControlListener != null) {//右滑
                    mDirectionControlListener.rightSlide();
                }
            }
        } else {//上滑或者下滑
            if (e1.getY() - e2.getY() > MIN_OFFSET_VALUE) {
                if (mDirectionControlListener != null) {//上滑
                    mDirectionControlListener.upSlide();
                }
            } else {
                if (mDirectionControlListener != null) {//下滑
                    mDirectionControlListener.downSlide();
                }
            }
        }
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Log.i(TAG, "onSingleTapConfirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.i(TAG, "onDoubleTap");

        if (mDirectionControlListener != null) {//双击事件
            mDirectionControlListener.doubleClick();
        }
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Log.i(TAG, "onDoubleTapEvent");
        return false;
    }

    public interface DirectionControlListener {
        void singleClick();

        void longClick();

        void doubleClick();

        void leftSlide();

        void rightSlide();

        void upSlide();

        void downSlide();
    }

    public void setControlStateListener(DirectionControlListener listener) {
        mDirectionControlListener = listener;
    }
}
