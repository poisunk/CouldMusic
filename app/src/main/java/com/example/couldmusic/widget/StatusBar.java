package com.example.couldmusic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class StatusBar extends View {


    private int mStatusBarHeight = 0;

    public StatusBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId != 0) {
            mStatusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int newHeightMS = MeasureSpec.makeMeasureSpec(mStatusBarHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightMS);
    }
}
