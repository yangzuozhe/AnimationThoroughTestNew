package com.example.animationthoroughtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

public class ValuesAnimatorActivity extends AppCompatActivity {
    Button mBtnValueButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_animation);
        mBtnValueButton1 = findViewById(R.id.btnValueButton1);
        mBtnValueButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                propertyValuesHolderFun();
                objectAnimatorFun();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void xmlAnimator2() {
        //通过 AnimatorInflater 的 loadAnimator 方法传入 value_animator_property_vaalues_holder.xml 文件
        final ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.value_animator_property_vaalues_holder);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //通过ValueAnimator 的 getAnimatedValue 方法获取当前的值
                final float number = (float) animation.getAnimatedValue();
                mBtnValueButton1.setText(String.valueOf(number));
            }
        });
        animator.start();
    }

    public void valueAnimator() {
        //动画设定值，可以设置多个数值， 这里注意是 ofFloat
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(200, 900.5f);
        //设置动画完成时间
        valueAnimator.setDuration(10000);
        //设置值变化监听器
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //上面是 ofFloat 这里必须为 float 否则报错
                float number = (float) animation.getAnimatedValue();
                mBtnValueButton1.setText(String.valueOf(number));
            }
        });
        //记住一定要开始运行动画
        valueAnimator.start();
    }

    public void argbAnimator() {
        //使用 ValueAnimator 的 ofArgb 方法，变换颜色
        final ValueAnimator valueAnimator = ValueAnimator.ofArgb(0xFFFF0000, 0xFF00FF00);
        valueAnimator.setDuration(3000);
        //改变数据
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int data = (int) animation.getAnimatedValue();
                //这里要注意要使用 setBackgroundColor 而不是使用 setBackgroundResource
                mBtnValueButton1.setBackgroundColor(data);
            }
        });
        valueAnimator.start();
    }

    ValueAnimator valueAnimator;

    public void objectEvaluator() {
        Height height1 = new Height();
        height1.setHeight(200);
        Height height2 = new Height();
        height2.setHeight(400);
        valueAnimator = ValueAnimator.ofObject(new HeightEvaluator(), height1, height2);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Height height = (Height) animation.getAnimatedValue();
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mBtnValueButton1.getLayoutParams();
                lp.height = height.getHeight();
                mBtnValueButton1.setLayoutParams(lp);
            }
        });
        valueAnimator.start();
    }

    public void myArgbAnimator() {
        ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), 0xFFFF0000, 0xFF00FF00);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBtnValueButton1.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        animator.start();
    }


    public void propertyValuesHolderFun() {
        //我们这里使用ValueAnimator将 PropertyValuesHolder 进行结合。
        // 注意这里和在ObjectAnimation中的用法不一样，这里的第一个参数是我们自定义的一个值，目的就是待会再通过 ValueAnimator 取值的时候可以区分
        final PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofInt("height", 200, 400);
        final PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofInt("width", 400, 600);
        final ValueAnimator valueAnimato = ValueAnimator.ofPropertyValuesHolder(valuesHolder1, valuesHolder2);
        valueAnimato.setDuration(3000);
        valueAnimato.setInterpolator(new LinearInterpolator());
        valueAnimato.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams lp = mBtnValueButton1.getLayoutParams();
                lp.height = (int) animation.getAnimatedValue("height");
                lp.width = (int) animation.getAnimatedValue("width");
                mBtnValueButton1.setLayoutParams(lp);

            }
        });
        valueAnimato.start();
    }


    public void objectAnimatorFun() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBtnValueButton1, View.ROTATION, 0, 270, 270, 0);
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }


}