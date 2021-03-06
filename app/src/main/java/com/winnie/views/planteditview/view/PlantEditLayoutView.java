package com.winnie.views.planteditview.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
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

    /**
     * 车牌号列表
     */
    private List<String> mPlantNumList = new ArrayList<>();

    /**
     * 车牌号长度，最多8位
     */
    private int mMaxSize = 8;

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
            x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked) {
                        return;
                    }
                    if (index > mPlantNumList.size()) {
                        mDialog.setCurrentIndex(mPlantNumList.size() - 1);
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
                mDialog.showNumView(index != 0);
                if (index > mPlantNumList.size() - 1) {
                    if (mPlantNumList.size() >= mMaxSize) {
                        return;
                    }
                    //新增的车牌号
                    mPlantNumList.add(text);
                } else {
                    //修改车牌号的某一位
                    mPlantNumList.set(index, text);
                }
                updatePlant(index);
            }

            @Override
            public void onSubString() {
                if (mPlantNumList.isEmpty()) {
                    return;
                }
                int index = mPlantNumList.size() - 1;
                mPlantNumList = mPlantNumList.subList(0, index);
                updatePlant(index - 1);
            }
        });
    }

    private void updatePlant(int oldIndex) {
        if (oldIndex + 1 < mRtnViewList.size()) {
            mRtnViewList.get(oldIndex + 1).setChecked(true);
        }

        for (RadioButton x : mRtnViewList) {
            int index = mRtnViewList.indexOf(x);
            if (index < mPlantNumList.size()) {
                x.setText(String.valueOf(mPlantNumList.get(index)));
                x.setSelected(true);
            } else {
                x.setText("");
                x.setSelected(false);
            }
        }
    }

    public String getPlantNum() {
        StringBuilder plantNum = new StringBuilder();
        for (String x : mPlantNumList) {
            plantNum.append(x);
        }
        return plantNum.toString();
    }
}
