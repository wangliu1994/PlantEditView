package com.winnie.views.planteditview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.winnie.views.planteditview.view.PlantEditLayoutView;

/**
 * @author winnie
 */
public class MainActivity extends AppCompatActivity {
    private PlantEditLayoutView mEditLayoutView;
    private Button mButton;
    private TextView mTvPlateNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditLayoutView = findViewById(R.id.pv_plant);
        mButton = findViewById(R.id.btn_ok);
        mTvPlateNum = findViewById(R.id.tv_plant_num);
        mButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTvPlateNum.setText(mEditLayoutView.getPlantNum());
                    }
                }
        );
    }
}
