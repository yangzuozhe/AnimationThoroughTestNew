package com.example.animationthoroughtest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.animationthoroughtest.bean.MyObjectBean;
import com.example.animationthoroughtest.bean.MyPoint;

public class ObjectAnimatorActivity extends AppCompatActivity {
    TextView mTvGreen;
    Button mBtnMyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        initView();

    }

    public void initView() {
        mTvGreen = findViewById(R.id.tvGreen);
        mBtnMyButton = findViewById(R.id.btnMyButton);
        mBtnMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorSet();
            }
        });
    }


    public void normalAnimator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTvGreen, View.ROTATION, 0, 270);
        objectAnimator.setDuration(3000);
        //无线循环，这里写 -1 也可以
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }

    public void pathAnimator() {
        //这里就表示从原来的位置，先移动到，向往对于原来位置（0，0）的100，100。然后再移动到相对于原来位置（0，0）的300，0
        Path path = new Path();
        //moveTo就是位置，这里的 （0，0）就表示当前控件摆放的初始位置
        path.moveTo(0, 0);
        //这就是相对于初始位置的 x 轴的100，y 轴的 100
        path.lineTo(100, 100);
        path.lineTo(300, 0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTvGreen, View.TRANSLATION_X, View.TRANSLATION_Y, path);
        animator.setDuration(3000);
        animator.start();
    }

    public void pathAnimator2() {
        //这里表示，从原来的位置移动到x轴100，然后放大2倍。接着移动到x轴300，缩小原来的0.2。接着移动到x轴400，放大为原来的三倍
        Path path = new Path();
        path.moveTo(0, 1);
        path.lineTo(100, 2f);
        path.lineTo(300, 0.2f);
        path.lineTo(400, 3f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTvGreen, View.TRANSLATION_X, View.SCALE_Y, path);
        animator.setDuration(3000);
        animator.start();
    }

    public void customAnimator() {
        MyIntEvaluator intEvaluator = new MyIntEvaluator();
        MyProperty property = new MyProperty(String.class, "text");
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(mTvGreen, property, intEvaluator, "1", "10");
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }


    class MyProperty extends Property<TextView, String> {


        /**
         * A constructor that takes an identifying name and {@link #getType() type} for the property.
         *
         * @param type
         * @param name
         */
        public MyProperty(Class<String> type, String name) {
            super(type, name);
        }

        @Override
        public String get(TextView object) {
            return object.getText().toString();
        }

        @Override
        public void set(TextView object, String value) {
            object.setText(value);
        }
    }


    class MyIntEvaluator implements TypeEvaluator<String> {
        @Override
        public String evaluate(float fraction, String startValue, String endValue) {
            int startInt = Integer.parseInt(startValue);
            int endInt = Integer.parseInt(endValue);
            int cur = (int) (startInt + fraction * (endInt - startInt));
            return cur + "";
        }
    }


    public void customAnimator2() {
        //这里我们重写匿名内部类Property，因为这样写比较省空间，如果像上面的那样子，继承实现的写法也是可以的
        //第一个参数是我们的控件类，第二个参数是下面我们 TypeEvaluator 的泛型，因此，可以理解为，第一个是控件，第二个参数是数据
        Property<TextView, MyObjectBean> property = new Property<TextView, MyObjectBean>(MyObjectBean.class, "myProperty") {
            @Override
            public void set(TextView object, MyObjectBean value) {
                //这里注意
                final ViewGroup.LayoutParams params = object.getLayoutParams();
                params.width = value.getWidth();
                params.height = value.getHeight();
                object.setLayoutParams(params);
            }

            @Override
            public MyObjectBean get(TextView object) {
                final ViewGroup.LayoutParams params = object.getLayoutParams();
                MyObjectBean bean = new MyObjectBean(params.height, params.width);
                return bean;
            }
        };

        //这里我们重写匿名内部类TypeEvaluator，因为这样写比较省空间，如果像上面的那样子，继承实现的写法也是可以的
        //这泛型就是我们的数据，我们在在估值器中进行计算
        TypeEvaluator<MyObjectBean> evaluator = new TypeEvaluator<MyObjectBean>() {
            @Override
            public MyObjectBean evaluate(float fraction, MyObjectBean startValue, MyObjectBean endValue) {
                int startWidth = startValue.getWidth();
                int startHeight = startValue.getHeight();
                int endWidth = endValue.getWidth();
                int endHeight = endValue.getHeight();
                int curWidth = (int) (startWidth + fraction * (endWidth - startWidth));
                int curHeight = (int) (startHeight + fraction * (endHeight - startHeight));
                final MyObjectBean bean = new MyObjectBean(curHeight, curWidth);
                return bean;
            }
        };
        final ViewGroup.LayoutParams params = mTvGreen.getLayoutParams();
        //这里要注意用 params.height, params.width 获取控件的宽高需要在xml中要让控件，赋予一个具体的值，否则不行
        MyObjectBean bean1 = new MyObjectBean(params.height, params.width);
        MyObjectBean bean2 = new MyObjectBean(params.height * 2, params.height * 2);
        ObjectAnimator animator = ObjectAnimator.ofObject(mTvGreen, property, evaluator, bean1, bean2);
        animator.setDuration(3000);
        animator.start();
    }


    class MyTypeEvaluator implements TypeEvaluator<MyObjectBean> {

        @Override
        public MyObjectBean evaluate(float fraction, MyObjectBean startValue, MyObjectBean endValue) {
            return null;
        }
    }


    public void argbAnimator() {
        //这里使用继承也可以
        Property<TextView, Integer> property = new Property<TextView, Integer>(Integer.class, "argbAnimator") {
            @Override
            public Integer get(TextView object) {
                Drawable drawable = object.getBackground();
                if (drawable instanceof ColorDrawable) {
                    return ((ColorDrawable) drawable).getColor();
                }
                return Color.YELLOW;
            }

            @Override
            public void set(TextView object, Integer value) {
//                super.set(object, value);这里得super一定要去掉
                object.setBackgroundColor(value);
            }
        };
        ObjectAnimator animator = ObjectAnimator.ofArgb(mTvGreen, property, Color.GREEN, Color.RED);
        animator.setDuration(3000);
        animator.start();
    }

    public void multiAnimator() {
        //注意这里是 TypeConverter 不是 TypeEvaluator,这里我们将MyPoint的 X 和 Y 值传进数组，然后到时候会传到我们自定义控件的 set 方法中
        TypeConverter<MyPoint, int[]> typeConverter = new TypeConverter<MyPoint, int[]>(MyPoint.class, int[].class) {
            @Override
            public int[] convert(MyPoint value) {
                int[] ints = {value.getX(), value.getY()};
                return ints;
            }
        };
        //这里是做估值，计算 x 和 y 的估值
        TypeEvaluator<MyPoint> typeEvaluator = new TypeEvaluator<MyPoint>() {
            @Override
            public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {
                final int startX = startValue.getX();
                final int startY = startValue.getY();
                final int endX = endValue.getX();
                final int endY = endValue.getY();
                final int curX = (int) (startX + fraction * (endX - startX));
                final int curY = (int) (startY + fraction * (endY - startY));
                final MyPoint point = new MyPoint(curX, curY);
                return point;
            }
        };
        MyPoint point = new MyPoint(100, 100);
        MyPoint point1 = new MyPoint(800, 800);
        ObjectAnimator objectAnimator = ObjectAnimator.ofMultiInt(mTvGreen, "yangPoint", typeConverter, typeEvaluator, point, point1);
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }


    public void objectAnimator2() {
        Property<TextView, MyPoint> property = new Property<TextView, MyPoint>(MyPoint.class, "myPoint") {

            @Override
            public void set(TextView object, MyPoint value) {
                object.setTranslationX(value.getX());
                object.setTranslationY(value.getY());
            }

            @Override
            public MyPoint get(TextView object) {
                return new MyPoint((int) object.getTranslationX(), (int) object.getTranslationY());
            }
        };
        TypeEvaluator<MyPoint> typeEvaluator = new TypeEvaluator<MyPoint>() {
            @Override
            public MyPoint evaluate(float fraction, MyPoint startValue, MyPoint endValue) {
                final int startX = startValue.getX();
                final int startY = startValue.getY();
                final int endX = endValue.getX();
                final int endY = endValue.getY();
                final int curX = (int) (startX + fraction * (endX - startX));
                final int curY = (int) (startY + fraction * (endY - startY));
                final MyPoint point = new MyPoint(curX, curY);
                return point;
            }
        };
        MyPoint point = new MyPoint(0, 0);
        MyPoint point1 = new MyPoint(200, 200);
        ObjectAnimator animator = ObjectAnimator.ofObject(mTvGreen, property, typeEvaluator, point, point1);
        animator.setDuration(3000);
        animator.start();
    }

    public void propertyValuesHolderAnimator() {
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1.2f);
        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.2f);
        //这里的keyframe表示的是：
        //第一个参数 表示动画完成度，第二个参数表示，所需要的运动
        //这里就表示，开始的时候没有变化
        Keyframe kf0 = Keyframe.ofFloat(0F, 0F);
        //当动画完成到50%的时候需要转360度
        Keyframe kf1 = Keyframe.ofFloat(0.5f, 360f);
        //当动画完成了100%的时候需要转回到0度
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofKeyframe(View.ROTATION, kf0, kf1, kf2);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTvGreen, valuesHolder, valuesHolder1, valuesHolder2);
        animator.setDuration(3000);
        animator.start();
    }

    public void animatorSet() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTvGreen, View.ROTATION, 0, 360);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mTvGreen, View.SCALE_Y, 1, 2f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTvGreen, View.SCALE_X, 1, 2f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mTvGreen, View.TRANSLATION_Y, 0, 250);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mTvGreen, View.TRANSLATION_Y, 0, 10);
        AnimatorSet set = new AnimatorSet();
       //这里的每个动画都要setRepeatCount(-1);不然就会
        animator.setRepeatCount(-1);
        animator1.setRepeatCount(-1);
        animator2.setRepeatCount(-1);
        animator3.setRepeatCount(-1);
        animator4.setRepeatCount(-1);
        //这里一定只能使用 playTogether 因为如果想要循环播放，那么就得一起播放动画，如果按顺序播放就会卡在第一个动画中
        set.playTogether(animator, animator1, animator2, animator3, animator4);
        set.setDuration(3000);
        set.start();
    }

}