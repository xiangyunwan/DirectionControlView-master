package com.zhan.directioncontrolview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhan.directioncontrolview.widget.DirectionControlView;

public class MainActivity extends AppCompatActivity
        implements DirectionControlView.DirectionControlListener {

    private DirectionControlView mDirectionControlView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mDirectionControlView.setControlStateListener(this);
    }

    private void initView() {
        mDirectionControlView = (DirectionControlView) findViewById(R.id.main_dcv);
        mTextView = (TextView) findViewById(R.id.main_tv);
    }

    private void showToast(String str) {
        mTextView.setText(str);
    }

    @Override
    public void singleClick() {
        showToast("单击");
    }

    @Override
    public void longClick() {
        showToast("长按");
    }

    @Override
    public void doubleClick() {
        showToast("双击");
    }

    @Override
    public void leftSlide() {
        showToast("左滑");
    }

    @Override
    public void rightSlide() {
        showToast("右滑");
    }

    @Override
    public void upSlide() {
        showToast("上滑");
    }

    @Override
    public void downSlide() {
        showToast("下滑");
    }
}
