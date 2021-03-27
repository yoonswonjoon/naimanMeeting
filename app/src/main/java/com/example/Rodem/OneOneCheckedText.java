package com.example.Rodem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

public class OneOneCheckedText extends androidx.appcompat.widget.AppCompatCheckedTextView {
    public OneOneCheckedText(Context context) {
        super(context);
    }

    public OneOneCheckedText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OneOneCheckedText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int w , int h){
        super.onMeasure(w,w);
    }
}
