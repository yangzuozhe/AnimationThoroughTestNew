package com.example.animationthoroughtest.widget;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.animationthoroughtest.bean.MyPoint;

/**
 * 类描述
 *
 * @author HB.yangzuozhe
 * @date 2020-11-20
 */
public class YangTextView extends androidx.appcompat.widget.AppCompatTextView {


    public YangTextView(Context context) {
        this(context, null);
    }

    public YangTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YangTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setYangText(int data1, int data2) {
        String data = "";
        data = data + data1 + data2;
        setText(data);
    }

    public String getYangText() {
        return getText().toString();
    }


    /**
     * 这里注意参数不是 MyPoint 因为我们使用前面 TypeConverter 传进来的是两个参数 也就相当于 MyPoint 的 X 和 Y 值
     * @param data1 X值
     * @param data2 Y值
     */
    public void setYangPoint(int data1 ,int data2) {
        setX(data1);
        setY(data2);
    }

    public MyPoint getYangPoint() {
        MyPoint point = new MyPoint((int)getX(),(int)getY());
        return point;
    }

}
