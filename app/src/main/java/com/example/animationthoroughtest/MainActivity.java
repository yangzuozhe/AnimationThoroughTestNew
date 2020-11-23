package com.example.animationthoroughtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mBtnButton1;
    Button mBtnButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnButton1 = findViewById(R.id.btnButton1);
        mBtnButton2 = findViewById(R.id.btnButton2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate1);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                toastShow("开始");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toastShow("结束");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                toastShow("重复动画");
            }
        });
        //这里的两个参数表示，第一个参数表示，动画运行了多长的时间，这里也就是运行了90%的时间。
        //第二个参数表示，动画完成的程度，这里完成的程度为1%
        //也就是说，这里的路径插值器表示，当运行了1%的动画的时候，时间才过去90%
        PathInterpolator pathInterpolator = new PathInterpolator(0.9f, 0.01f);
        animation.setInterpolator(pathInterpolator);
        mBtnButton1.setAnimation(animation);
    }

    public void toastShow(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}