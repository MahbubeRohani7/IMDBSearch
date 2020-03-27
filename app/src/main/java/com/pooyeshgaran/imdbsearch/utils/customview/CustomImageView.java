package com.pooyeshgaran.imdbsearch.utils.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;


public class CustomImageView extends androidx.appcompat.widget.AppCompatImageView {

        private static final float ASPECT_RATIO = 1.5f;

    public CustomImageView(Context context) {
            super(context);
        }

    public CustomImageView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = Math.round(width * ASPECT_RATIO);
            setMeasuredDimension(width, height);
        }
    }


