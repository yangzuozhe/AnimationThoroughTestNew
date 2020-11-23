package com.example.animationthoroughtest.shareAnimator;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.example.animationthoroughtest.R;

public class ShareOneActivity extends AppCompatActivity {
    ImageView mIvShareA;
    ImageView mIvShareA_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_one);
        mIvShareA = findViewById(R.id.ivShareA);
        mIvShareA_2 = findViewById(R.id.ivShareA_2);

//        //进入动画
//        getWindow().setEnterTransition(new Explode().setDuration(500).setInterpolator(new AccelerateInterpolator()));
//        //退出动画
//        getWindow().setReturnTransition(new Explode().setDuration(500));
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);// 允许使用transitions
        //滑动进入动画
        Explode enterAinmator = new Explode();
        //滑动退出动画
        Explode exitAnimator = new Explode();
        enterAinmator.setDuration(3000).setInterpolator(new LinearInterpolator());
        exitAnimator.setDuration(3000);
                //进入动画
        getWindow().setEnterTransition(enterAinmator);
        //退出动画
        getWindow().setReturnTransition(exitAnimator);
        mIvShareA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreAnimator();
            }
        });
    }
    public void normalAnimator(){
        Pair pair1 = new Pair(mIvShareA, "shareAnimator");
        Pair pair2 = new Pair(mIvShareA_2, "shareAnimator2");
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(ShareOneActivity.this, pair1, pair2).toBundle();
        Intent intent = new Intent(ShareOneActivity.this, ShareTwoActivity.class);
        startActivity(intent, bundle);
    }
    public void moreAnimator(){
        Intent intent = new Intent(ShareOneActivity.this, ShareTwoActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }
}