package com.example.animationthoroughtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;

public class ViewPropertyAnimatorActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnClick;
    ImageView mIvImg1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animator);
        initView();
    }
    public void initView(){
        mBtnClick = findViewById(R.id.btnClick);
        mBtnClick.setOnClickListener(this);
        mIvImg1 = findViewById(R.id.ivImg1);
    }
    public void initAnimator(){
        ViewPropertyAnimator viewPropertyAnimator = mIvImg1.animate();
        viewPropertyAnimator.setDuration(2000);
        viewPropertyAnimator.translationY(300);
        viewPropertyAnimator.start();
        viewPropertyAnimator.translationX(300);
        viewPropertyAnimator.alpha(0);
    }

    @Override
    public void onClick(View v) {
        final int i = v.getId();
        if (i == R.id.btnClick){
            initAnimator();

        }
    }
}