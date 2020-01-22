package com.winnie.views.planteditview.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.winnie.views.planteditview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : winnie
 * @date : 2020/1/19
 * @desc
 */
public class PlantEditLayoutView extends FrameLayout {
    private PlantOwnDialog mDialog;

    private List<RadioButton> mRtnViewList = new ArrayList<>();

    private List<String> mPlantNumList = new ArrayList<>();

    public PlantEditLayoutView(@NonNull Context context) {
        super(context);
        initView();
    }

    public PlantEditLayoutView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PlantEditLayoutView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_plant_view, this);
        RadioButton rtnPlantOwn = findViewById(R.id.rtn_plant_own);
        mRtnViewList.add(rtnPlantOwn);
        RadioButton rtnPlantNum1 = findViewById(R.id.rtn_plant_num1);
        mRtnViewList.add(rtnPlantNum1);
        RadioButton rtnPlantNum2 = findViewById(R.id.rtn_plant_num2);
        mRtnViewList.add(rtnPlantNum2);
        RadioButton rtnPlantNum3 = findViewById(R.id.rtn_plant_num3);
        mRtnViewList.add(rtnPlantNum3);
        RadioButton rtnPlantNum4 = findViewById(R.id.rtn_plant_num4);
        mRtnViewList.add(rtnPlantNum4);
        RadioButton rtnPlantNum5 = findViewById(R.id.rtn_plant_num5);
        mRtnViewList.add(rtnPlantNum5);
        RadioButton rtnPlantNum6 = findViewById(R.id.rtn_plant_num6);
        mRtnViewList.add(rtnPlantNum6);
        RadioButton rtnPlantNum7 = findViewById(R.id.rtn_plant_num7);
        mRtnViewList.add(rtnPlantNum7);
        for (RadioButton x : mRtnViewList) {
            final int index = mRtnViewList.indexOf(x);
            x.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 这里的交互有问题，还需要理一理
                    if (index > mPlantNumList.size()) {
                        mDialog.setCurrentIndex(mPlantNumList.size() -1);
                        mDialog.showNumView(mPlantNumList.size() != 0);
                        mDialog.showAtLocation(PlantEditLayoutView.this, Gravity.BOTTOM, 0, 0);
                    } else {
                        mDialog.setCurrentIndex(index);
                        mDialog.showNumView(index != 0);
                        mDialog.showAtLocation(PlantEditLayoutView.this, Gravity.BOTTOM, 0, 0);
                    }
                }
            });
        }

        mDialog = new PlantOwnDialog(getContext());
        mDialog.setOnPlantChangeListener(new PlantOwnDialog.OnPlantChangeListener() {
            @Override
            public void onAppend(int index, String text) {
                if (mPlantNumList.size() >= 8) {
                    return;
                }
                mDialog.showNumView(index != 0);
                if (index == -1) {
                    mPlantNumList.add(text);
                } else {
                    mPlantNumList.set(index, text);
                }
                updatePlant();
            }

            @Override
            public void onSubString() {
                if (mPlantNumList.isEmpty()) {
                    return;
                }
                mPlantNumList = mPlantNumList.subList(0, mPlantNumList.size() - 1);
                updatePlant();
            }
        });
    }

    private void updatePlant() {
        int currentIndex;
        if (mPlantNumList.isEmpty()) {
            currentIndex = -1;
        } else {
            currentIndex = mPlantNumList.size() - 1;
        }
        if (currentIndex + 1 < mRtnViewList.size()) {
            mRtnViewList.get(currentIndex + 1).setChecked(true);
        }

        for (RadioButton x : mRtnViewList) {
            int index = mRtnViewList.indexOf(x);
            if (index < mPlantNumList.size()) {
                x.setText(String.valueOf(mPlantNumList.get(index)));
            } else {
                x.setText("");
            }
        }
    }
}
