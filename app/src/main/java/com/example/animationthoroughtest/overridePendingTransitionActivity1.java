package com.example.animationthoroughtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class overridePendingTransitionActivity1 extends AppCompatActivity implements View.OnClickListener {
    Button mBtnActivity1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.start_enter_from_left,0);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.start_enter_from_left,0);
        setContentView(R.layout.activity_override_pending_transition1);
        mBtnActivity1 = findViewById(R.id.btnActivity1);
        mBtnActivity1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnActivity1) {
            Intent intent = new Intent(overridePendingTransitionActivity1.this, overridePendingTransitionActivity2.class);
            startActivity(intent);
            //第一个参数，跳转到指定Activity的进场动画。
            //第二个参数，本Activity的退出的过场动画
//            overridePendingTransition(R.anim.start_enter_from_left, R.anim.over_set_animation);
        }
    }
}