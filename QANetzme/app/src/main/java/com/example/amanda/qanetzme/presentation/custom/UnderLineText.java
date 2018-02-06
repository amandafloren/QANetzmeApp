package com.example.amanda.qanetzme.presentation.custom;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by amanda on 31/01/18.
 */

public class UnderLineText {
    private TextView mTextView;

    public UnderLineText(TextView textView){
        this.mTextView = textView;
        if (mTextView != null){
            PaintText(mTextView);
        }
    }

    private void PaintText(TextView textView){
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
