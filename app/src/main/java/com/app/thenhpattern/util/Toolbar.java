package com.app.thenhpattern.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.thenhpattern.R;

public class Toolbar extends androidx.appcompat.widget.Toolbar implements View.OnClickListener {

    private ImageView leftIcon, rightIcon;
    private TextView headerTitle;
    Boolean isLeft;
    Boolean isRight;

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    Boolean isRequired;

    private View.OnClickListener leftClickListener, rightClickListener;


    public Toolbar(final Context context) {
        this(context, null);
    }

    public Toolbar(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Toolbar(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (!isInEditMode()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
        }

        setId(R.id.app_toolbar);
        setFocusable(true);
        setFocusableInTouchMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(0);
        }

        LayoutInflater.from(getContext()).inflate(R.layout.app_toolbar, this, true);

        leftIcon = findViewById(R.id.left_icon);
        rightIcon = findViewById(R.id.right_icon);
        headerTitle = findViewById(R.id.header_title);

        leftIcon.setOnClickListener(this);
        rightIcon.setOnClickListener(this);
    }

    public void setMainVisibility(Boolean aBoolean) {
        if(aBoolean) {
            this.setVisibility(VISIBLE);
        }else{
            this.setVisibility(GONE);
        }
    }

    public void setTitle(String textTitle) {
        if (textTitle == null)
            textTitle = "";
        headerTitle.setText(textTitle);
    }

    public String getTitle() {
        if(headerTitle != null) {
            return headerTitle.getText().toString();
        }
        return null;
    }

    public void performBack() {
        leftIcon.performClick();
    }

    public void setHasLeft(boolean hasBack) {
        if (hasBack)
            leftIcon.setVisibility(View.VISIBLE);
        else
            leftIcon.setVisibility(View.GONE);
    }

    public void setFragmentHasLeft(Boolean isLeft) {
        this.isLeft = isLeft;
    }

    public void setFragmentHasRight(Boolean isRight) {
        this.isRight = isRight;
    }

    public Boolean getFragmentHasLeft() {
        return isLeft;
    }

    public Boolean getFragmentHasRight() {
        return isRight;
    }

    public Boolean getHasLeft() {
        if (leftIcon.getVisibility() == View.VISIBLE){
            return true;
        } else
            return false;
    }

    public Boolean getHasRight() {
        if (rightIcon.getVisibility() == View.VISIBLE){
            return true;
        } else
            return false;
    }

    public void setHasRight(boolean hasNotification) {
        if (hasNotification)
            rightIcon.setVisibility(View.VISIBLE);
        else
            rightIcon.setVisibility(View.GONE);
    }

    public void setLeftClickListener(OnClickListener onClickListener) {
        this.leftClickListener = onClickListener;
    }

    public OnClickListener getLeftClickListener() {
        return leftClickListener;
    }

    public OnClickListener getRightClickListener() {
        return rightClickListener;
    }

    public void setRightClickListener(OnClickListener onClickListener) {
        this.rightClickListener = onClickListener;
    }

    @Override
    public void onClick(View view) {
        if (view == leftIcon) {
            if (leftClickListener != null) {
                leftClickListener.onClick(view);
            } else {
                // handle back button
                try {
                    if (getContext() instanceof Activity)
                        ((Activity) getContext()).onBackPressed();
                    else if (getRootView().getContext() instanceof Activity)
                        ((Activity) getRootView().getContext()).onBackPressed();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (view == rightIcon) {
            if (rightClickListener != null)
                rightClickListener.onClick(view);
        }
    }

}
