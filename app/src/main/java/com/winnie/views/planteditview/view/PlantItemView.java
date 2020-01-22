package com.winnie.views.planteditview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author : winnie
 * @date : 2020/1/20
 * @desc
 */
public class PlantItemView extends CompoundButton {
    private OnItemClickListener mOnItemClickListener;

    public PlantItemView(Context context) {
        super(context);
        initView();
    }

    public PlantItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PlantItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (mOnItemClickListener != null) {
//                        mOnItemClickListener.onClick(getText().toString());
//                    }
//                    return true;
//                }
//                return false;
//            }
//        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onClick(getText().toString());
                }
            }
        });
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        /**
         * 车票号被点击选中
         * @param itemText 选中的字母或者数字
         */
        void onClick(String itemText);
    }

}
