package com.example.animationthoroughtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class overridePendingTransitionActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_override_pending_transition2);
    }

    @Override
    public void finish() {
        super.finish();
        //退出当前Activity页面的时候，
        // 第一个参数表示，我们要返回的页面从顶部下来，显示出页面
        //第二个参数表示，我们当前的Activity 页面从当前的位置，滑倒底部，直至消失不见
        //这个动画是连贯的
//        overridePendingTransition(R.anim.finish_enter_from_top,R.anim.finish_exit_to_bottom);
    }
}