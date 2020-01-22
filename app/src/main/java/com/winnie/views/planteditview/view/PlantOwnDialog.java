package com.winnie.views.planteditview.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;

import com.winnie.views.planteditview.R;

/**
 * @author : winnie
 * @date : 2020/1/20
 * @desc
 */
public class PlantOwnDialog extends PopupWindow {
    private View mContentView;
    private View mPlantOwnView;
    private View mPlantNumView;

    private OnPlantChangeListener mOnPlantChangeListener;

    private int mCurrentIndex = -1;
    private boolean mShowNumView = false;

    public PlantOwnDialog(@NonNull Context context) {
        super(context);
        mContentView = LayoutInflater.from(context).inflate(R.layout.dialog_plant, null);
        setContentView(mContentView);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.PopWindowAnimStyle);
        setBackgroundDrawable(new BitmapDrawable());
        initView();
    }

    private void initView() {
        FrameLayout rootView = mContentView.findViewById(R.id.layout_container);
        recursionPlantItem(rootView);

        mPlantOwnView = mContentView.findViewById(R.id.ll_plant_own_container);
        mPlantNumView = mContentView.findViewById(R.id.ll_plant_num_container);

        mContentView.findViewById(R.id.iv_close_num_dialog).setOnClickListener(mOnCloseClickListener);
        mContentView.findViewById(R.id.iv_close_own_dialog).setOnClickListener(mOnCloseClickListener);

        mContentView.findViewById(R.id.iv_delete_num).setOnClickListener(mOnDeleteClickListener);
        mContentView. findViewById(R.id.iv_delete_own).setOnClickListener(mOnDeleteClickListener);

        mContentView.findViewById(R.id.tv_change_num).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNumView(true);
            }
        });
        mContentView.findViewById(R.id.tv_change_own).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNumView(false);
            }
        });
    }

    /**
     * 递归所有View，找到PlantItemView并为其设置点击监听
     */
    private void recursionPlantItem(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof ViewGroup) {
                recursionPlantItem((ViewGroup) childView);
            }

            if (childView instanceof PlantItemView) {
                ((PlantItemView) childView).setOnItemClickListener(
                        new PlantItemView.OnItemClickListener() {
                            @Override
                            public void onClick(String itemText) {
                                if (mOnPlantChangeListener != null) {
                                    mOnPlantChangeListener.onAppend(mCurrentIndex, itemText);
                                }
                            }
                        });
            }
        }
    }

    private View.OnClickListener mOnDeleteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnPlantChangeListener != null) {
                mOnPlantChangeListener.onSubString();
            }
        }
    };

    private View.OnClickListener mOnCloseClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    public void setCurrentIndex(int currentIndex) {
        this.mCurrentIndex = currentIndex;
    }

    public void showNumView(boolean showNumView) {
        this.mShowNumView = showNumView;
        if(showNumView){
            mPlantOwnView.setVisibility(View.GONE);
            mPlantNumView.setVisibility(View.VISIBLE);
        }else {
            mPlantOwnView.setVisibility(View.VISIBLE);
            mPlantNumView.setVisibility(View.GONE);
        }
    }

    public void setOnPlantChangeListener(OnPlantChangeListener onPlantChangeListener) {
        mOnPlantChangeListener = onPlantChangeListener;
    }

    public interface OnPlantChangeListener {
        /**
         * 新增一位车牌号
         *
         * @param index 索引
         * @param text 车牌号
         */
        void onAppend(int index, String text);

        /**
         * 回退删除一位车牌
         */
        void onSubString();
    }
}
