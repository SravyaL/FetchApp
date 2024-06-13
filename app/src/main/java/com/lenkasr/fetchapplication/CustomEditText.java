package com.lenkasr.fetchapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

public class CustomEditText extends androidx.appcompat.widget.AppCompatEditText {


    private Drawable clearButton;

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        clearButton = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getX() >= (getWidth() - getPaddingRight() - clearButton.getIntrinsicWidth())) {
                    getText().clear();
                    hideClearButton();
                    return true;
                }
            }
            return false;
        });
    }

    private void showClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, clearButton, null);
    }

    private void hideClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }


}
