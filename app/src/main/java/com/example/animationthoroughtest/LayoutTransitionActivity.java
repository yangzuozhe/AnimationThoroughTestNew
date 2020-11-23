package com.example.animationthoroughtest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutTransitionActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnAddView;
    Button mBtnDeleteView;
    LinearLayout mLlTransitionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);
        initView();
        initChangeAnimator();
    }

    public void initAnimator() {
        //初始化 LayoutTransition 布局动画
        LayoutTransition layoutTransition = new LayoutTransition();

        //自定义添移除view动画
        Animator addViewAnimator = ObjectAnimator.ofFloat(null, View.TRANSLATION_X, 0, 100).setDuration(3000);
        //LayoutTransition.DISAPPEARING 表示view被移除
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, addViewAnimator);
        //注意设置动画是在 layoutTransition布局动画 这里设置的
        layoutTransition.setDuration(3000);

        //自定义添加view动画
        Animator deleteViewAnimator = ObjectAnimator.ofFloat(null, View.ROTATION, 0, 360);
        //LayoutTransition.APPEARING 表示 view添加
        layoutTransition.setAnimator(LayoutTransition.APPEARING, deleteViewAnimator);
        //注意设置动画是在 layoutTransition布局动画 这里设置的
        layoutTransition.setDuration(3000);

        //将我们的父布局通过 setLayoutTransition 设置布局动画
        mLlTransitionActivity.setLayoutTransition(layoutTransition);
    }

    public void initChangeAnimator() {
        //初始化 LayoutTransition 布局动画
        LayoutTransition layoutTransition = new LayoutTransition();
        //这里需要设置
        PropertyValuesHolder changeLeft =
                PropertyValuesHolder.ofInt("left",0, 0);
        PropertyValuesHolder changeTop =
                PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder changeRight =
                PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder changeBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 0);

        PropertyValuesHolder aniChanApp = PropertyValuesHolder.ofFloat("rotation", 0, 360, 0);

        ObjectAnimator removeViewAnimator = ObjectAnimator.ofPropertyValuesHolder(this,changeLeft,changeTop,aniChanApp);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,removeViewAnimator);


        mLlTransitionActivity.setLayoutTransition(layoutTransition);
    }

    public void initView() {
        mBtnAddView = findViewById(R.id.btnAddView);
        mBtnDeleteView = findViewById(R.id.btnDeleteView);
        mLlTransitionActivity = findViewById(R.id.llTransitionActivity);
        mBtnDeleteView.setOnClickListener(this);
        mBtnAddView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int i = v.getId();
        if (i == R.id.btnAddView) {
            Button button = new Button(getApplicationContext());
            button.setPadding(20, 20, 20, 20);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLlTransitionActivity.addView(button, layoutParams);
        } else if (i == R.id.btnDeleteView) {
            int count = mLlTransitionActivity.getChildCount();
            if (count >= 3) {
                //删除第三个Button
                mLlTransitionActivity.removeViewAt(2);
            }
        }
    }
}